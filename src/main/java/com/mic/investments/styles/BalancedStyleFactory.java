package com.mic.investments.styles;

public class BalancedStyleFactory implements StyleAbstractFactory {

    private Integer nationalValue;
    private Integer foreignValue;
    private Integer financialValue;

    public BalancedStyleFactory(Integer nationalValue, Integer foreignValue, Integer financialValue) {
        this.nationalValue = nationalValue;
        this.foreignValue = foreignValue;
        this.financialValue = financialValue;
    }

    @Override
    public Style createStyle() {
        return new BalancedStyle(nationalValue, foreignValue, financialValue);
    }

}

