package com.peerstreet.coding.challenge.populationgrowthapi.controller;

import com.peerstreet.coding.challenge.populationgrowthapi.model.PopulationGrowth;
import com.peerstreet.coding.challenge.populationgrowthapi.service.PopulationGrowthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PopulationGrowthController {

    @Autowired
    private PopulationGrowthService populationGrowthService;

    @GetMapping("/v1/zipcode/{zipcode}/populationGrowth")
    public PopulationGrowth getPopulationGrowthByZipcode(@PathVariable("zipcode") final String zipcode) throws Exception {
        return populationGrowthService.getPopulationGrowth(zipcode);
    }
}
