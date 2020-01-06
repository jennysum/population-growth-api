package com.peerstreet.coding.challenge.populationgrowthapi.validator;

import org.springframework.stereotype.Component;

@Component
public class PopulationGrowthValidator {

    public boolean isValid(String zipcode) {
        if(zipcode == null
                || zipcode.isEmpty()
                || !(zipcode.matches("[0-9]+"))
                || zipcode.length() != 5) {
            return false;
        }
        return true;
    }
}
