package com.mic.investments.utils;

import com.mic.investments.funds.FundType;
import com.mic.investments.styles.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StyleUtilTest {


    @Test
    void testGetStyleValue_safeStyle_nationalType() {

        Integer expectedResult = 20;

        Style style = StyleFactory.getStyle(new SafeStyleFactory(20, 75, 5));
        FundType fundType = FundType.NATIONAL;

        Integer actualResult = StyleUtil.getStyleValue(style, fundType);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetStyleValue_safeStyle_foreignType() {

        Integer expectedResult = 75;

        Style style = StyleFactory.getStyle(new SafeStyleFactory(20, 75, 5));
        FundType fundType = FundType.FOREIGN;

        Integer actualResult = StyleUtil.getStyleValue(style, fundType);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetStyleValue_safeStyle_financialType() {

        Integer expectedResult = 5;

        Style style = StyleFactory.getStyle(new SafeStyleFactory(20, 75, 5));
        FundType fundType = FundType.FINANCIAL;

        Integer actualResult = StyleUtil.getStyleValue(style, fundType);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetStyleValue_balancedStyle_nationalType() {

        Integer expectedResult = 30;

        Style style = StyleFactory.getStyle(new BalancedStyleFactory(30, 60, 10));
        FundType fundType = FundType.NATIONAL;

        Integer actualResult = StyleUtil.getStyleValue(style, fundType);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetStyleValue_balancedStyle_foreignType() {

        Integer expectedResult = 60;

        Style style = StyleFactory.getStyle(new BalancedStyleFactory(30, 60, 10));
        FundType fundType = FundType.FOREIGN;

        Integer actualResult = StyleUtil.getStyleValue(style, fundType);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetStyleValue_balancedStyle_financialType() {

        Integer expectedResult = 10;

        Style style = StyleFactory.getStyle(new BalancedStyleFactory(30, 60, 10));
        FundType fundType = FundType.FINANCIAL;

        Integer actualResult = StyleUtil.getStyleValue(style, fundType);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetStyleValue_offensiveStyle_nationalType() {

        Integer expectedResult = 35;

        Style style = StyleFactory.getStyle(new OffensiveStyleFactory(35, 20, 45));
        FundType fundType = FundType.NATIONAL;

        Integer actualResult = StyleUtil.getStyleValue(style, fundType);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetStyleValue_offensiveStyle_foreignType() {

        Integer expectedResult = 20;

        Style style = StyleFactory.getStyle(new OffensiveStyleFactory(35, 20, 45));
        FundType fundType = FundType.FOREIGN;

        Integer actualResult = StyleUtil.getStyleValue(style, fundType);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetStyleValue_offensiveStyle_financialType() {

        Integer expectedResult = 45;

        Style style = StyleFactory.getStyle(new OffensiveStyleFactory(35, 20, 45));
        FundType fundType = FundType.FINANCIAL;

        Integer actualResult = StyleUtil.getStyleValue(style, fundType);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetStyleValue_offensiveStyle_nullType() {

        Integer expectedResult = 0;

        Style style = StyleFactory.getStyle(new OffensiveStyleFactory(35, 20, 45));

        Integer actualResult = StyleUtil.getStyleValue(style, FundType.ALL);

        assertEquals(expectedResult, actualResult);
    }

}
