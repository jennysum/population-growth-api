package com.peerstreet.coding.challenge.populationgrowthapi.service;

import com.peerstreet.coding.challenge.populationgrowthapi.exception.ResourceNotFoundException;
import com.peerstreet.coding.challenge.populationgrowthapi.mapper.PopulationGrowthMapper;
import com.peerstreet.coding.challenge.populationgrowthapi.model.entity.CbsaToMsaEntity;
import com.peerstreet.coding.challenge.populationgrowthapi.model.PopulationGrowth;
import com.peerstreet.coding.challenge.populationgrowthapi.repository.CbsaToMsaRepository;
import com.peerstreet.coding.challenge.populationgrowthapi.repository.ZipcodeToCbsaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PopulationGrowthServiceTest {

    @Mock
    private ZipcodeToCbsaRepository zipcodeToCbsaRepository;

    @Mock
    private CbsaToMsaRepository cbsaToMsaRepository;

    @Mock
    PopulationGrowthMapper populationGrowthMapper;

    private PopulationGrowthService populationGrowthService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        populationGrowthService = new PopulationGrowthService(zipcodeToCbsaRepository, cbsaToMsaRepository, populationGrowthMapper);
    }

    @Test
    public void shouldGetPopulationGrowthWithProperCbsaFromMdiv() throws Exception {
        String zipcode = "zipcode";
        String cbsa = "cbsa";
        String finalCbsa = "finalCbsa";
        String lsad = "Metropolitan Statistical Area";

        CbsaToMsaEntity cbsaToMsaEntity = new CbsaToMsaEntity();
        PopulationGrowth populationGrowth = new PopulationGrowth();

        when(zipcodeToCbsaRepository.findCbsaByZipcode(zipcode)).thenReturn(cbsa);
        when(cbsaToMsaRepository.findCbsaByMdiv(cbsa)).thenReturn(finalCbsa);
        when(cbsaToMsaRepository.findMsaByCbsa(finalCbsa, lsad)).thenReturn(cbsaToMsaEntity);
        when(populationGrowthMapper.toPopulationGrowth(zipcode, cbsaToMsaEntity)).thenReturn(populationGrowth);

        assertEquals(populationGrowth, populationGrowthService.getPopulationGrowth(zipcode));
    }

    @Test
    public void shouldGetPopulationGrowthWithNoCbsaFromMdiv() throws Exception {
        String zipcode = "zipcode";
        String cbsa = "cbsa";
        String lsad = "Metropolitan Statistical Area";

        CbsaToMsaEntity cbsaToMsaEntity = new CbsaToMsaEntity();
        PopulationGrowth populationGrowth = new PopulationGrowth();

        when(zipcodeToCbsaRepository.findCbsaByZipcode(zipcode)).thenReturn(cbsa);
        when(cbsaToMsaRepository.findCbsaByMdiv(cbsa)).thenReturn(null);
        when(cbsaToMsaRepository.findMsaByCbsa(cbsa, lsad)).thenReturn(cbsaToMsaEntity);
        when(populationGrowthMapper.toPopulationGrowth(zipcode, cbsaToMsaEntity)).thenReturn(populationGrowth);

        assertEquals(populationGrowth, populationGrowthService.getPopulationGrowth(zipcode));
    }

    @Test
    public void shouldThrowResourceNotFoundExceptionIfCbsaIsNull() {
        String zipcode = "zipcode";

        when(zipcodeToCbsaRepository.findCbsaByZipcode(zipcode)).thenReturn(null);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            populationGrowthService.getPopulationGrowth(zipcode);
        });
    }

    @Test
    public void shouldThrowResourceNotFoundExceptionIfCbsaIs99999() {
        String zipcode = "zipcode";

        when(zipcodeToCbsaRepository.findCbsaByZipcode(zipcode)).thenReturn("99999");

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            populationGrowthService.getPopulationGrowth(zipcode);
        });
    }

    @Test
    public void shouldThrowResourceNotFoundExceptionIfCbsaToMsaEntityIsNull() {
        String zipcode = "zipcode";
        String cbsa = "cbsa";
        String finalCbsa = "finalCbsa";
        String lsad = "Metropolitan Statistical Area";

        when(zipcodeToCbsaRepository.findCbsaByZipcode(zipcode)).thenReturn(cbsa);
        when(cbsaToMsaRepository.findCbsaByMdiv(cbsa)).thenReturn(finalCbsa);
        when(cbsaToMsaRepository.findMsaByCbsa(cbsa, lsad)).thenReturn(null);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            populationGrowthService.getPopulationGrowth(zipcode);
        });
    }
}