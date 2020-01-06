package com.peerstreet.coding.challenge.populationgrowthapi.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopulationGrowthValidatorTest {

    private PopulationGrowthValidator populationGrowthValidator;

    @BeforeEach
    public void setUp() {
        populationGrowthValidator = new PopulationGrowthValidator();
    }

    @Test
    public void shouldReturnTrueIfZipcodeIsValid() {
        String zipcode = "60660";

        assertTrue(populationGrowthValidator.isValid(zipcode));
    }

    @Test
    public void shouldReturnFalseIfZipcodeIsEmpty() {
        String zipcode = "";

        assertFalse(populationGrowthValidator.isValid(zipcode));
    }

    @Test
    public void shouldReturnFalseIfZipcodeIsNull() {
        String zipcode = null;

        assertFalse(populationGrowthValidator.isValid(zipcode));
    }

    @Test
    public void shouldReturnFalseIfZipcodeIsNotNumeric() {
        String zipcode = "zipcode";

        assertFalse(populationGrowthValidator.isValid(zipcode));
    }

    @Test
    public void shouldReturnFalseIfZipcodeIsNotFiveCharacterLongNumber() {
        String zipcode = "123456";

        assertFalse(populationGrowthValidator.isValid(zipcode));
    }
}