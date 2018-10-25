package com.mic.investments.styles;

public class OffensiveStyleFactory implements StyleAbstractFactory {

    private Integer nationalValue;
    private Integer foreignValue;
    private Integer financialValue;

    public OffensiveStyleFactory(Integer nationalValue, Integer foreignValue, Integer financialValue) {
        this.nationalValue = nationalValue;
        this.foreignValue = foreignValue;
        this.financialValue = financialValue;
    }

    @Override
    public Style createStyle() {
        return new OffensiveStyle(nationalValue, foreignValue, financialValue);
    }

}

