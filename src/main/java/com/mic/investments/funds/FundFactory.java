package com.mic.investments.funds;

import java.math.BigDecimal;

public class FundFactory {

    private FundFactory() {

    }

    public static Fund getFund(int id, String name, FundType type, BigDecimal percentage, BigDecimal amount) {

        if (FundType.NATIONAL.equals(type) || FundType.FOREIGN.equals(type) || FundType.FINANCIAL.equals(type)) {
            return new FundResult(id, name, type, percentage, amount);
        } else {
            return new Fund(id, name, type);
        }
    }

}

