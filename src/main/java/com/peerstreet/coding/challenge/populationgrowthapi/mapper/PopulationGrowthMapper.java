package com.peerstreet.coding.challenge.populationgrowthapi.mapper;

import com.peerstreet.coding.challenge.populationgrowthapi.model.entity.CbsaToMsaEntity;
import com.peerstreet.coding.challenge.populationgrowthapi.model.Population;
import com.peerstreet.coding.challenge.populationgrowthapi.model.PopulationGrowth;
import org.springframework.stereotype.Component;

@Component
public class PopulationGrowthMapper {
    public PopulationGrowth toPopulationGrowth(String zipcode, CbsaToMsaEntity cbsaToMsaEntity) {
        PopulationGrowth populationGrowth = new PopulationGrowth();

        Population population = new Population();
        population.setForYear2014(cbsaToMsaEntity.getPopestimate2014());
        population.setForYear2015(cbsaToMsaEntity.getPopestimate2015());

        populationGrowth.setZipcode(zipcode);
        populationGrowth.setCbsa(cbsaToMsaEntity.getCbsa());
        populationGrowth.setMsaName(cbsaToMsaEntity.getName());
        populationGrowth.setPopulation(population);

        return populationGrowth;
    }
}
