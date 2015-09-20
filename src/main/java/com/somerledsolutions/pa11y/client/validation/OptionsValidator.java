package com.somerledsolutions.pa11y.client.validation;

import com.somerledsolutions.pa11y.client.model.AccessibilityStandard;
import org.apache.commons.cli.CommandLine;
import org.springframework.stereotype.Component;

@Component
public class OptionsValidator {

    public boolean isAccessiblityStandardValid(String standard) {

        for(AccessibilityStandard validStandard: AccessibilityStandard.values()) {
            if(validStandard.name().equals(standard)){
                return true;
            }
        }
        return false;
    }

    public boolean validateCreateOptions(CommandLine commandLine) {
        return commandLine.hasOption("n") && commandLine.hasOption("u") && commandLine.hasOption("s");
    }

    public boolean validateListOptions(CommandLine commandLine) {
        return commandLine.hasOption("u");
    }

    public boolean validateRunOptions(CommandLine commandLine) {
        return commandLine.hasOption("u") && commandLine.hasOption("tid");
    }
}
