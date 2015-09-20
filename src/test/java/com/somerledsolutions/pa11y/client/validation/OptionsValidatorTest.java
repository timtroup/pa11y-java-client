package com.somerledsolutions.pa11y.client.validation;

import com.somerledsolutions.pa11y.client.model.AccessibilityStandard;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void testValidateCreateOptionsWithNameAndHostAndStandard() throws Exception {

        CommandLine mockCommandLine = mock(CommandLine.class);
        when(mockCommandLine.hasOption("n")).thenReturn(true);
        when(mockCommandLine.hasOption("u")).thenReturn(true);
        when(mockCommandLine.hasOption("s")).thenReturn(true);

        assertTrue(testee.validateCreateOptions(mockCommandLine));
    }

    @Test
    public void testValidateCreateOptionsWithMissingNameOption() throws Exception {

        CommandLine mockCommandLine = mock(CommandLine.class);
        when(mockCommandLine.hasOption("n")).thenReturn(false);
        when(mockCommandLine.hasOption("u")).thenReturn(true);
        when(mockCommandLine.hasOption("s")).thenReturn(true);

        assertFalse(testee.validateCreateOptions(mockCommandLine));
    }

    @Test
    public void testValidateCreateOptionsWithMissingUrlOption() throws Exception {

        CommandLine mockCommandLine = mock(CommandLine.class);
        when(mockCommandLine.hasOption("n")).thenReturn(true);
        when(mockCommandLine.hasOption("u")).thenReturn(false);
        when(mockCommandLine.hasOption("s")).thenReturn(true);

        assertFalse(testee.validateCreateOptions(mockCommandLine));
    }

    @Test
    public void testValidateCreateOptionsWithMissingStandardOption() throws Exception {

        CommandLine mockCommandLine = mock(CommandLine.class);
        when(mockCommandLine.hasOption("n")).thenReturn(true);
        when(mockCommandLine.hasOption("u")).thenReturn(true);
        when(mockCommandLine.hasOption("s")).thenReturn(false);

        assertFalse(testee.validateCreateOptions(mockCommandLine));
    }

    @Test
    public void testvalidateListOptionsWithUrlOption() throws Exception {

        CommandLine mockCommandLine = mock(CommandLine.class);
        when(mockCommandLine.hasOption("u")).thenReturn(true);

        assertTrue(testee.validateListOptions(mockCommandLine));
    }

    @Test
    public void testvalidateListOptionsWithMissingUrlOption() throws Exception {

        CommandLine mockCommandLine = mock(CommandLine.class);
        when(mockCommandLine.hasOption("u")).thenReturn(false);

        assertFalse(testee.validateListOptions(mockCommandLine));
    }
}