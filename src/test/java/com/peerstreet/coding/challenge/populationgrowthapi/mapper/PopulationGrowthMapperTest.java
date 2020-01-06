package com.peerstreet.coding.challenge.populationgrowthapi.mapper;

import com.peerstreet.coding.challenge.populationgrowthapi.model.entity.CbsaToMsaEntity;
import com.peerstreet.coding.challenge.populationgrowthapi.model.Population;
import com.peerstreet.coding.challenge.populationgrowthapi.model.PopulationGrowth;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PopulationGrowthMapperTest {

    private static PopulationGrowthMapper populationGrowthMapper;

    @BeforeAll
    public static void setUp() {
        populationGrowthMapper = new PopulationGrowthMapper();
    }

    @Test
    public void shouldMapToPopulationGrowthWhenZipcodeAndCsbaToMsaEntityAreValid() {
        String zipcode = "76437";
        String cbsa = "10180";
        String mdiv = "41980";
        String lsad = "Metropolitan Statistical Area";
        String name = "Abilene, TX";
        String popestimate2014 = "168380";
        String popestimate2015 = "169578";

        CbsaToMsaEntity cbsaToMsaEntity = new CbsaToMsaEntity();
        cbsaToMsaEntity.setCbsa(cbsa);
        cbsaToMsaEntity.setMdiv(mdiv);
        cbsaToMsaEntity.setLsad(lsad);
        cbsaToMsaEntity.setName(name);
        cbsaToMsaEntity.setPopestimate2014(popestimate2014);
        cbsaToMsaEntity.setPopestimate2015(popestimate2015);

        Population expectedPopulation = new Population();
        expectedPopulation.setForYear2014(popestimate2014);
        expectedPopulation.setForYear2015(popestimate2015);

        PopulationGrowth expectedPopulationGrowth = new PopulationGrowth();
        expectedPopulationGrowth.setZipcode(zipcode);
        expectedPopulationGrowth.setCbsa(cbsa);
        expectedPopulationGrowth.setMsaName(name);
        expectedPopulationGrowth.setPopulation(expectedPopulation);

        PopulationGrowth actual = populationGrowthMapper.toPopulationGrowth(zipcode, cbsaToMsaEntity);

        assertEquals(zipcode, actual.getZipcode());
        assertEquals(expectedPopulationGrowth.getCbsa(), actual.getCbsa());
        assertEquals(expectedPopulationGrowth.getMsaName(), actual.getMsaName());
        assertEquals(expectedPopulation.getForYear2014(), actual.getPopulation().getForYear2014());
        assertEquals(expectedPopulation.getForYear2015(), actual.getPopulation().getForYear2015());
    }
}