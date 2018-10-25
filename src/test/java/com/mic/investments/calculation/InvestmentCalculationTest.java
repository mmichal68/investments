package com.mic.investments.calculation;

import com.mic.investments.funds.*;
import com.mic.investments.styles.SafeStyleFactory;
import com.mic.investments.styles.Style;
import com.mic.investments.styles.StyleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvestmentCalculationTest {

    private InvestmentCalculation investmentCalculation;

    @BeforeEach
    void setUp() {
        investmentCalculation = new InvestmentCalculation();
    }

    @Test
    void testProcessFunds_safeStyle_noRemainder() {
        List<Fund> funds = createInputFunds();

        BigDecimal amount = BigDecimal.valueOf(10000);

        Style style = createSafeStyle();

        List<Fund> actualResult = investmentCalculation.processFunds(funds, style, amount);

        assertThat(actualResult, containsInAnyOrder(
                FundFactory.getFund(0, "Fundusz Polski 1", FundType.NATIONAL,
                        BigDecimal.valueOf(10.00).setScale(2, RoundingMode.DOWN),
                        BigDecimal.valueOf(1000.00).setScale(2, RoundingMode.DOWN)),
                FundFactory.getFund(1, "Fundusz Polski 2", FundType.NATIONAL,
                        BigDecimal.valueOf(10.00).setScale(2, RoundingMode.DOWN),
                        BigDecimal.valueOf(1000.00).setScale(2, RoundingMode.DOWN)),
                FundFactory.getFund(2, "Fundusz Zagraniczny 1", FundType.FOREIGN,
                        BigDecimal.valueOf(25.00).setScale(2, RoundingMode.DOWN),
                        BigDecimal.valueOf(2500.00).setScale(2, RoundingMode.DOWN)),
                FundFactory.getFund(3, "Fundusz Zagraniczny 2", FundType.FOREIGN,
                        BigDecimal.valueOf(25.00).setScale(2, RoundingMode.DOWN),
                        BigDecimal.valueOf(2500.00).setScale(2, RoundingMode.DOWN)),
                FundFactory.getFund(4, "Fundusz Zagraniczny 3", FundType.FOREIGN,
                        BigDecimal.valueOf(25.00).setScale(2,RoundingMode.DOWN),
                        BigDecimal.valueOf(2500.00).setScale(2, RoundingMode.DOWN)),
                FundFactory.getFund(5, "Fundusz Pieniezny 1", FundType.FINANCIAL,
                        BigDecimal.valueOf(5.00).setScale(2, RoundingMode.DOWN),
                        BigDecimal.valueOf(500.00).setScale(2, RoundingMode.DOWN))
                )
        );
    }

    @Test
    void testProcessFunds_fundPercentageRemainder() {
        List<Fund> funds = createInputFundsSecondCase();

        BigDecimal amount = BigDecimal.valueOf(10000);

        Style style = createSafeStyle();

        List<Fund> actualResult = investmentCalculation.processFunds(funds, style, amount);

        assertThat(actualResult, containsInAnyOrder(
                FundFactory.getFund(0, "Fundusz Polski 1", FundType.NATIONAL,
                        BigDecimal.valueOf(6.68).setScale(2, RoundingMode.DOWN),
                        BigDecimal.valueOf(668.00).setScale(2, RoundingMode.DOWN)),
                FundFactory.getFund(1, "Fundusz Polski 2", FundType.NATIONAL,
                        BigDecimal.valueOf(6.66).setScale(2, RoundingMode.DOWN),
                        BigDecimal.valueOf(666.00).setScale(2, RoundingMode.DOWN)),
                FundFactory.getFund(2, "Fundusz Polski 3", FundType.NATIONAL,
                        BigDecimal.valueOf(6.66).setScale(2, RoundingMode.DOWN),
                        BigDecimal.valueOf(666.00).setScale(2, RoundingMode.DOWN)),
                FundFactory.getFund(3, "Fundusz Zagraniczny 2", FundType.FOREIGN,
                        BigDecimal.valueOf(37.50).setScale(2, RoundingMode.DOWN),
                        BigDecimal.valueOf(3750.00).setScale(2, RoundingMode.DOWN)),
                FundFactory.getFund(4, "Fundusz Zagraniczny 3", FundType.FOREIGN,
                        BigDecimal.valueOf(37.50).setScale(2,RoundingMode.DOWN),
                        BigDecimal.valueOf(3750.00).setScale(2, RoundingMode.DOWN)),
                FundFactory.getFund(5, "Fundusz Pieniezny 1", FundType.FINANCIAL,
                        BigDecimal.valueOf(5.00).setScale(2, RoundingMode.DOWN),
                        BigDecimal.valueOf(500.00).setScale(2, RoundingMode.DOWN))
        ));
    }

    @Test
    void testProcessFunds_emptyList() {

        List<Fund> funds = new ArrayList<>();

        Style style = createSafeStyle();

        BigDecimal amount = BigDecimal.valueOf(10001);

        List<Fund> actualResult = investmentCalculation.processFunds(funds, style, amount);

        assertTrue(actualResult.isEmpty());

    }

    @Test
    void testProcessFunds_nullFunds() {

        Style style = createSafeStyle();

        BigDecimal amount = BigDecimal.valueOf(10001);

        List<Fund> actualResult = investmentCalculation.processFunds(null, style, amount);

        assertTrue(actualResult.isEmpty());

    }
    @Test
    void testProcessFunds_nullAmount() {

        List<Fund> funds = createMinDummyList();

        Style style = createSafeStyle();

        List<Fund> actualResult = investmentCalculation.processFunds(funds, style, null);

        assertTrue(actualResult.isEmpty());

    }
    @Test
    void testProcessFunds_nullStyle() {

        List<Fund> funds = createMinDummyList();

        BigDecimal amount = BigDecimal.valueOf(10001);

        List<Fund> actualResult = investmentCalculation.processFunds(funds, null, amount);

        assertTrue(actualResult.isEmpty());

    }

    @Test
    void testCalcRemainder_MinList() {
        BigDecimal expectedResult = BigDecimal.ONE;

        BigDecimal amount = BigDecimal.valueOf(1001);

        List<Fund> funds = createMinDummyList();

        BigDecimal actualResult = investmentCalculation.calcRemainder(funds, amount);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void testCalcRemainder_normalList() {

        BigDecimal expectedResult = BigDecimal.ONE;

        List<Fund> funds = createDummyList();

        BigDecimal amount = BigDecimal.valueOf(10001);

        BigDecimal actualResult = investmentCalculation.calcRemainder(funds, amount);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void testCalcRemainder_normalList_noRemainder() {

        BigDecimal expectedResult = BigDecimal.ZERO;

        List<Fund> funds = createDummyList();

        BigDecimal amount = BigDecimal.valueOf(10000);

        BigDecimal actualResult = investmentCalculation.calcRemainder(funds, amount);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void testCalcRemainder_fundsWithoutAmounts() {

        BigDecimal expectedResult = BigDecimal.valueOf(10000);

        List<Fund> funds = createInputFunds();

        BigDecimal amount = BigDecimal.valueOf(10000);

        BigDecimal actualResult = investmentCalculation.calcRemainder(funds, amount);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void testCalcRemainder_nullFunds() {

        BigDecimal expectedResult = BigDecimal.ZERO.setScale(2, RoundingMode.DOWN);

        List<Fund> funds = null;

        BigDecimal amount = BigDecimal.valueOf(10000);

        BigDecimal actualResult = investmentCalculation.calcRemainder(funds, amount);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void testCalcRemainder_nullAmount() {

        BigDecimal expectedResult = BigDecimal.ZERO.setScale(2, RoundingMode.DOWN);

        List<Fund> funds = createMinDummyList();

        BigDecimal amount = null;

        BigDecimal actualResult = investmentCalculation.calcRemainder(funds, amount);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void testCalcRemainder_emptyList() {

        BigDecimal expectedResult = BigDecimal.ONE;

        List<Fund> funds = createDummyListSecondCase();

        BigDecimal amount = BigDecimal.valueOf(10001);

        BigDecimal actualResult = investmentCalculation.calcRemainder(funds, amount);

        assertEquals(expectedResult, actualResult);

    }


    private Style createSafeStyle() {
        return StyleFactory.getStyle(new SafeStyleFactory(20, 75, 5));
    }

    private List<Fund> createMinDummyList() {

        return List.of(FundFactory.getFund(0, "", FundType.NATIONAL,
                BigDecimal.valueOf(15), BigDecimal.valueOf(150)),
                FundFactory.getFund(1, "", FundType.FOREIGN,
                        BigDecimal.valueOf(35), BigDecimal.valueOf(350)),
                FundFactory.getFund(2, "", FundType.FINANCIAL,
                        BigDecimal.valueOf(50), BigDecimal.valueOf(500)));
    }

    private List<Fund> createDummyList() {
        return List.of(
                // national funds
                FundFactory.getFund(0, "Fundusz Polski 1", FundType.NATIONAL, BigDecimal.valueOf(10),
                        BigDecimal.valueOf(1000)),
                FundFactory.getFund(1, "Fundusz Polski 2", FundType.NATIONAL, BigDecimal.valueOf(10),
                        BigDecimal.valueOf(1000)),
                // foreign funds
                FundFactory.getFund(2, "Fundusz Zagraniczny 1", FundType.FOREIGN, BigDecimal.valueOf(25),
                        BigDecimal.valueOf(2500)),
                FundFactory.getFund(3, "Fundusz Zagraniczny 2", FundType.FOREIGN, BigDecimal.valueOf(25),
                        BigDecimal.valueOf(2500)),
                FundFactory.getFund(4, "Fundusz Zagraniczny 3", FundType.FOREIGN, BigDecimal.valueOf(25),
                        BigDecimal.valueOf(2500)),
                // financial funds
                FundFactory.getFund(5, "Fundusz Pieniezny 1", FundType.FINANCIAL, BigDecimal.valueOf(5),
                        BigDecimal.valueOf(500))

        );
    }

    private List<Fund> createDummyListSecondCase() {
        return List.of(
                // national funds
                FundFactory.getFund(0, "Fundusz Polski 1", FundType.NATIONAL, BigDecimal.valueOf(5),
                        BigDecimal.valueOf(500)),
                FundFactory.getFund(1, "Fundusz Polski 2", FundType.NATIONAL, BigDecimal.valueOf(5),
                        BigDecimal.valueOf(500)),
                // foreign funds
                FundFactory.getFund(2, "Fundusz Zagraniczny 1", FundType.FOREIGN, BigDecimal.valueOf(20),
                        BigDecimal.valueOf(2000)),
                FundFactory.getFund(3, "Fundusz Zagraniczny 2", FundType.FOREIGN, BigDecimal.valueOf(20),
                        BigDecimal.valueOf(2000)),
                FundFactory.getFund(4, "Fundusz Pieniezny 1", FundType.FINANCIAL, BigDecimal.valueOf(25),
                        BigDecimal.valueOf(2500)),
                // financial funds
                FundFactory.getFund(5, "Fundusz Pieniezny 2", FundType.FINANCIAL, BigDecimal.valueOf(25),
                        BigDecimal.valueOf(2500))

        );
    }

    private List<Fund> createInputFunds() {
        return List.of(
                // national funds
                new Fund(0, "Fundusz Polski 1", FundType.NATIONAL),
                new Fund(1, "Fundusz Polski 2", FundType.NATIONAL),
                // foreign funds
                new Fund(2, "Fundusz Zagraniczny 1", FundType.FOREIGN),
                new Fund(3, "Fundusz Zagraniczny 2", FundType.FOREIGN),
                new Fund(4, "Fundusz Zagraniczny 3", FundType.FOREIGN),
                // financial funds
                new Fund(5, "Fundusz Pieniezny 1", FundType.FINANCIAL)

        );
    }

    private List<Fund> createInputFundsSecondCase() {
        return List.of(
                // national funds
                new Fund(0, "Fundusz Polski 1", FundType.NATIONAL),
                new Fund(1, "Fundusz Polski 2", FundType.NATIONAL),
                new Fund(2, "Fundusz Polski 3", FundType.NATIONAL),
                // foreign funds
                new Fund(3, "Fundusz Zagraniczny 2", FundType.FOREIGN),
                new Fund(4, "Fundusz Zagraniczny 3", FundType.FOREIGN),
                // financial funds
                new Fund(5, "Fundusz Pieniezny 1", FundType.FINANCIAL)

        );
    }

}
