package com.mic.investments.styles;

public class SafeStyleFactory implements StyleAbstractFactory {

    private Integer nationalValue;
    private Integer foreignValue;
    private Integer financialValue;

    public SafeStyleFactory(Integer nationalValue, Integer foreignValue, Integer financialValue) {
        this.nationalValue = nationalValue;
        this.foreignValue = foreignValue;
        this.financialValue = financialValue;
    }

    @Override
    public Style createStyle() {
        return new SafeStyle(nationalValue, foreignValue, financialValue);
    }

}

