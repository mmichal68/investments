package com.mic.investments.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class CalculationUtil {

    private CalculationUtil() {

    }

    /**
     * Divide
     *
     * @param dividend dividend
     * @param divisor divisor
     * @return quotient
     */
    public static BigDecimal divide(Integer dividend, Integer divisor) {
        return BigDecimal.valueOf((double) dividend / divisor).setScale(2, RoundingMode.DOWN);
    }

    /**
     * Calculate the remainder of division
     *
     * @param v1 percentage
     * @param v2 percentage per unit
     * @param v3 units size
     * @return result
     */
    public static BigDecimal calcRemainder(Integer v1, BigDecimal v2, Integer v3) {
        return BigDecimal.valueOf(v1).subtract(v2.multiply(BigDecimal.valueOf(v3)));
    }

    /**
     * Add
     *
     * @param firstValue value
     * @param secondValue value
     * @return sum
     */
    public static BigDecimal add(BigDecimal firstValue, BigDecimal secondValue) {
        return firstValue.add(secondValue);
    }

    /**
     * Substract
     *
     * @param minuend    minuend
     * @param subtrahend subtrahend
     * @return deviation
     */
    public static BigDecimal substract(BigDecimal minuend, BigDecimal subtrahend) {
        return minuend.subtract(subtrahend);
    }

    /**
     * Calculate percentage value
     *
     * @param value value
     * @param percentage percentage
     * @return result
     */
    public static BigDecimal calcPercentageValue(BigDecimal value, BigDecimal percentage) {
        return value.multiply(percentage).divide(new BigDecimal(100)).setScale(2, RoundingMode.DOWN);
    }

}
