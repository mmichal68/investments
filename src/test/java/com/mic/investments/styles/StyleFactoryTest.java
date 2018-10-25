package com.mic.investments.styles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StyleFactoryTest {

    @Test
    void testGetStyle_safe() {

        Style style = StyleFactory.getStyle(new SafeStyleFactory(1, 1, 1));

        assertTrue(style instanceof SafeStyle);
    }

    @Test
    void testGetStyle_balanced() {
        Style style = StyleFactory.getStyle(new BalancedStyleFactory(1,1,1));

        assertTrue(style instanceof BalancedStyle);
    }

    @Test
    void testGetStyle_offensive() {
        Style style = StyleFactory.getStyle(new OffensiveStyleFactory(1,1,1));

        assertTrue(style instanceof OffensiveStyle);
    }

}