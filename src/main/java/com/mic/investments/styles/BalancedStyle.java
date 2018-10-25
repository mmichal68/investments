package com.mic.investments.styles;

public class BalancedStyle extends Style {

    private Integer nationalValue;
    private Integer foreignValue;
    private Integer financialValue;

    public BalancedStyle(Integer nationalValue, Integer foreignValue, Integer financialValue) {
        this.nationalValue = nationalValue;
        this.foreignValue = foreignValue;
        this.financialValue = financialValue;
    }

    @Override
    public Integer getNationalValue() {
        return this.nationalValue;
    }

    @Override
    public Integer getForeignValue() {
        return this.foreignValue;
    }

    @Override
    public Integer getFinancialValue() {
        return this.financialValue;
    }

}

