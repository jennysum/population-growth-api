package com.peerstreet.coding.challenge.populationgrowthapi.controller;

import com.peerstreet.coding.challenge.populationgrowthapi.model.PopulationGrowth;
import com.peerstreet.coding.challenge.populationgrowthapi.service.PopulationGrowthService;
import com.peerstreet.coding.challenge.populationgrowthapi.validator.PopulationGrowthValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PopulationGrowthControllerTest {

    @Mock
    PopulationGrowthService populationGrowthService;

    @Mock
    PopulationGrowthValidator populationGrowthValidator;

    PopulationGrowthController populationGrowthController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        populationGrowthController = new PopulationGrowthController(populationGrowthService, populationGrowthValidator);
    }

    @Test
    public void shouldReturnHttpStatusOkAndServiceIsCalledWhenZipcodeValid() throws Exception {
        String zipcode = "60660";
        PopulationGrowth expected = new PopulationGrowth();

        when(populationGrowthValidator.isValid(zipcode)).thenReturn(true);
        when(populationGrowthService.getPopulationGrowth(zipcode)).thenReturn(expected);

        assertEquals(new ResponseEntity<>(expected, HttpStatus.OK), populationGrowthController.getPopulationGrowthByZipcode(zipcode));
        verify(populationGrowthService).getPopulationGrowth(zipcode);
    }

    @Test
    public void shouldThrowValidationExceptionWhenZipcodeNotValid() throws Exception {
        String zipcode = "not valid zipcode";

        when(populationGrowthValidator.isValid(zipcode)).thenReturn(false);

        Assertions.assertThrows(ValidationException.class, () -> {
           populationGrowthController.getPopulationGrowthByZipcode(zipcode);
        });
    }
}