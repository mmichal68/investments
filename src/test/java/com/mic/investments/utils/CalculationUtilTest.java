package com.mic.investments.utils;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculationUtilTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testCalcPercentage() {
        BigDecimal expectedResult = BigDecimal.valueOf(6.66);

        BigDecimal actualResult = CalculationUtil.divide(20, 3);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testCalcRemainder_Reminder() {

        BigDecimal expectedResult = BigDecimal.valueOf(2);

        Integer value1 = 3;
        BigDecimal value2 = BigDecimal.ONE;
        Integer value3 = 1;

        BigDecimal actualResult = CalculationUtil.calcRemainder(value1, value2, value3);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testCalcRemainder_noRemonder() {

        BigDecimal expectedResult = BigDecimal.ZERO;

        Integer value1 = 1;
        BigDecimal value2 = BigDecimal.ONE;
        Integer value3 = 1;

        BigDecimal actualResult = CalculationUtil.calcRemainder(value1, value2, value3);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testAdd() {
        BigDecimal expectedResult = BigDecimal.TEN;

        BigDecimal actualResult = CalculationUtil.add(BigDecimal.valueOf(5), BigDecimal.valueOf(5));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testSubstract() {
        BigDecimal expectefResult = BigDecimal.ONE;

        BigDecimal actualResult = CalculationUtil.substract(BigDecimal.valueOf(5), BigDecimal.valueOf(4));

        assertEquals(expectefResult, actualResult);
    }

    @Test
    void testCalculateAmount_amountPerFund() {

        BigDecimal expectedResult = BigDecimal.ZERO.setScale(2);

        BigDecimal amount = BigDecimal.TEN;
        BigDecimal percent = BigDecimal.ZERO;
        BigDecimal actualResult = CalculationUtil.calcPercentageValue(amount, percent);

        assertEquals(expectedResult, actualResult);
    }

}
