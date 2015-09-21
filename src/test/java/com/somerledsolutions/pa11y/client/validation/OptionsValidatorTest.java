/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015. Tim Troup
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

    @Test
    public void testValidateRunOptionsWithTaskIdAndUrl() throws Exception {

        CommandLine mockCommandLine = mock(CommandLine.class);
        when(mockCommandLine.hasOption("tid")).thenReturn(true);
        when(mockCommandLine.hasOption("u")).thenReturn(true);

        assertTrue(testee.validateRunOptions(mockCommandLine));
    }

    @Test
    public void testValidateRunOptionsWithMissingTaskIdOPtion() throws Exception {

        CommandLine mockCommandLine = mock(CommandLine.class);
        when(mockCommandLine.hasOption("tid")).thenReturn(false);
        when(mockCommandLine.hasOption("u")).thenReturn(true);

        assertFalse(testee.validateRunOptions(mockCommandLine));
    }

    @Test
    public void testValidateRunOptionsWithMissingUrlOPtion() throws Exception {

        CommandLine mockCommandLine = mock(CommandLine.class);
        when(mockCommandLine.hasOption("tid")).thenReturn(true);
        when(mockCommandLine.hasOption("u")).thenReturn(false);

        assertFalse(testee.validateRunOptions(mockCommandLine));
    }
}