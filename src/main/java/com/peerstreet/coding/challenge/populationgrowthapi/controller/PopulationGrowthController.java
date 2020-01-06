package com.peerstreet.coding.challenge.populationgrowthapi.controller;

import com.peerstreet.coding.challenge.populationgrowthapi.model.PopulationGrowth;
import com.peerstreet.coding.challenge.populationgrowthapi.service.PopulationGrowthService;
import com.peerstreet.coding.challenge.populationgrowthapi.validator.PopulationGrowthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;

@RestController
public class PopulationGrowthController {

    @Autowired
    private PopulationGrowthService populationGrowthService;

    @Autowired
    private PopulationGrowthValidator populationGrowthValidator;

    public PopulationGrowthController(PopulationGrowthService populationGrowthService, PopulationGrowthValidator populationGrowthValidator) {
        this.populationGrowthService = populationGrowthService;
        this.populationGrowthValidator = populationGrowthValidator;
    }

    @GetMapping("/v1/zipcode/{zipcode}/populationGrowth")
    public ResponseEntity<PopulationGrowth> getPopulationGrowthByZipcode(@PathVariable("zipcode") final String zipcode) throws Exception {
        if (!(populationGrowthValidator.isValid(zipcode))) {
            throw new ValidationException("Zipcode is invalid.");
        }

        return ResponseEntity.ok(populationGrowthService.getPopulationGrowth(zipcode));
    }
}
