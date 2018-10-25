package com.mic.investments.funds;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FundFactoryTest {

    @Test
    void test() {
        Fund actualResult = FundFactory.getFund(1, "name", FundType.NATIONAL, BigDecimal.ONE, BigDecimal.TEN);

        assertTrue(actualResult instanceof FundResult);
    }

    @Test
    void tes2() {
        Fund actualResult = FundFactory.getFund(1, "name", FundType.ALL, BigDecimal.ONE, BigDecimal.TEN);

        assertFalse(actualResult instanceof FundResult);
    }

}