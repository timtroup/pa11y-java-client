package com.somerledsolutions.pa11y.client.validation;

import com.somerledsolutions.pa11y.client.model.AccessibilityStandard;

public class OptionsValidator {

    public boolean isAccessiblityStandardValid(String standard) {

        for(AccessibilityStandard validStandard: AccessibilityStandard.values()) {
            if(validStandard.name().equals(standard)){
                return true;
            }
        }
        return false;
    }

}
