package com.somerledsolutions.pa11y.client.validation;

import com.somerledsolutions.pa11y.client.model.AccessibilityStandard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OptionsValidatorTest {

    private OptionsValidator testee;

    @Before
    public void setUp() {
        testee = new OptionsValidator();
    }

    @Test
    public void testIsAccessiblityStandardValid() throws Exception {
        assertTrue(testee.isAccessiblityStandardValid(AccessibilityStandard.SECTION508.name()));
        assertTrue(testee.isAccessiblityStandardValid(AccessibilityStandard.WCAG2A.name()));
        assertTrue(testee.isAccessiblityStandardValid(AccessibilityStandard.WCAG2AA.name()));
        assertTrue(testee.isAccessiblityStandardValid(AccessibilityStandard.WCAG2AAA.name()));
        assertFalse(testee.isAccessiblityStandardValid("IAmNotAStandard"));
    }
}