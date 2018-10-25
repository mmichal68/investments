package com.mic.investments.styles;

public class StyleFactory {

    private StyleFactory() {

    }

    public static Style getStyle(StyleAbstractFactory abstractFactory) {
        return abstractFactory.createStyle();
    }

}

