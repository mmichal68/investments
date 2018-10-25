package com.mic.investments.utils;

import com.mic.investments.funds.FundType;
import com.mic.investments.styles.Style;

public final class StyleUtil {

    private StyleUtil() {

    }

    public static Integer getStyleValue(Style style, FundType fundType) {

        switch (fundType) {
            case NATIONAL:
                return style.getNationalValue();
            case FOREIGN:
                return style.getForeignValue();
            case FINANCIAL:
                return style.getFinancialValue();
            default:
                return 0;
        }
    }

}
