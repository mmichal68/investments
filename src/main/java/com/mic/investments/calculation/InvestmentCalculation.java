package com.mic.investments.calculation;

import com.mic.investments.funds.*;
import com.mic.investments.styles.Style;
import com.mic.investments.utils.CalculationUtil;
import com.mic.investments.utils.StyleUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class InvestmentCalculation {

    /**
     * Process all funds
     *
     * @param funds list of funds
     * @param style investment style
     * @param amount amount to invest
     * @return processed list with calculated amounts per fund
     */
    public List<Fund> processFunds(List<Fund> funds, Style style, BigDecimal amount) {

        if (funds == null || style == null || amount == null) {
            return Collections.emptyList();
        }

        Map<FundType, List<Fund>> fundMap = groupFunds(funds);

        fundMap = getItems(fundMap, style, amount);

        return convertMapToList(fundMap);
    }

    /**
     * Calculate the remainder amount
     *
     * @param funds  list of funds
     * @param amount amount to invest
     * @return the reminder amount
     */
    public BigDecimal calcRemainder(List<Fund> funds, BigDecimal amount) {
        if (funds == null || amount == null) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.DOWN);
        } else {
            return amount.subtract(sumAllAmounts(funds));
        }
    }

    /**
     * Group fund list by fundType and convert it to map
     *
     * @param funds list of funds
     * @return map
     */
    private Map<FundType, List<Fund>> groupFunds(List<Fund> funds) {
        return funds.stream().collect(Collectors.groupingBy(Fund::getType));
    }

    /**
     * Convert map to list
     *
     * @param map of funds, key fundType
     * @return list of funds
     */
    private List<Fund> convertMapToList(Map<FundType, List<Fund>> map) {
        return map.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * Sum all amounts from list
     *
     * @param funds list of funds
     * @return sum of all amounts from list
     */
    private BigDecimal sumAllAmounts(List<Fund> funds) {
        BigDecimal restAmount = BigDecimal.ZERO;

        for (Fund f : funds) {
            if (FundResult.class.isAssignableFrom(f.getClass())) {
                restAmount = CalculationUtil.add(restAmount, ((FundResult) f).getAmount());
            }
        }
        return restAmount;
    }

    /**
     * Collect calculated items
     *
     * @param items  map with list of founds
     * @param style  investment style
     * @param amount amount to invest
     * @return map with calculated percentage and amount
     */
    private Map<FundType, List<Fund>> getItems(Map<FundType, List<Fund>> items, Style style, BigDecimal amount) {
        return items.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> renderFunds(e.getValue(), style, amount)));
    }

    /**
     * Calculate percentage and amount
     *
     * @param funds  list of funds (only one FundType)
     * @param style  investment style
     * @param amount amount to invest
     * @return list of funds with calculated percentage and amount
     */
    private List<Fund> renderFunds(List<Fund> funds, Style style, BigDecimal amount) {

        int fundsSize = funds.size();

        Integer percentageFromStyle = StyleUtil.getStyleValue(style, funds.get(0).getType());

        BigDecimal percentagePerFund = CalculationUtil.divide(percentageFromStyle, fundsSize);
        BigDecimal amountPerFund = CalculationUtil.calcPercentageValue(amount, percentagePerFund);

        BigDecimal remainder = CalculationUtil.calcRemainder(percentageFromStyle, percentagePerFund, fundsSize);

        if (BigDecimal.ZERO.compareTo(remainder) == 0) {
            return addPercentAmountWithoutRemainder(funds, percentagePerFund, amountPerFund);
        } else {

            BigDecimal remainderPercentage = CalculationUtil.add(percentagePerFund, remainder);
            BigDecimal amoutWithRemainder = CalculationUtil.calcPercentageValue(amount, remainderPercentage);

            return addPercentAmountWithRemainder(funds, remainderPercentage, amoutWithRemainder, percentagePerFund,
                    amountPerFund);
        }
    }

    /**
     * Add calculated percentage and amount to list with reminder
     *
     * @param funds           list of funds
     * @param firstPercentage first fund percentage
     * @param firstAmount     first fund amount
     * @param percentage      other funds percentage
     * @param amount          other funds amount
     * @return updated list
     */
    private List<Fund> addPercentAmountWithRemainder(List<Fund> funds, BigDecimal firstPercentage,
                                                     BigDecimal firstAmount, BigDecimal percentage, BigDecimal amount) {
        List<Fund> resultList = new ArrayList<>();

        resultList.addAll(funds.stream().limit(1).parallel().map(
                fund -> FundFactory.getFund(fund.getId(), fund.getName(), fund.getType(), firstPercentage, firstAmount))
                .collect(Collectors.toList()));
        resultList.addAll(funds.stream().skip(1).parallel()
                .map(fund -> FundFactory.getFund(fund.getId(), fund.getName(), fund.getType(), percentage, amount))
                .collect(Collectors.toList()));
        return resultList;
    }

    /**
     * Add calculated percentage and amount to list without reminder
     *
     * @param funds    list of funds
     * @param amount   amount per fund
     * @param percents percentage per fund
     * @return updated list
     */
    private List<Fund> addPercentAmountWithoutRemainder(List<Fund> funds, BigDecimal amount, BigDecimal percents) {
        return funds.stream().parallel()
                .map(fund -> FundFactory.getFund(fund.getId(), fund.getName(), fund.getType(), amount, percents))
                .collect(Collectors.toList());
    }

}

