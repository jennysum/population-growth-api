package com.peerstreet.coding.challenge.populationgrowthapi.service;

import com.peerstreet.coding.challenge.populationgrowthapi.exception.ResourceNotFoundException;
import com.peerstreet.coding.challenge.populationgrowthapi.mapper.PopulationGrowthMapper;
import com.peerstreet.coding.challenge.populationgrowthapi.model.entity.CbsaToMsaEntity;
import com.peerstreet.coding.challenge.populationgrowthapi.model.PopulationGrowth;
import com.peerstreet.coding.challenge.populationgrowthapi.repository.CbsaToMsaRepository;
import com.peerstreet.coding.challenge.populationgrowthapi.repository.ZipcodeToCbsaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PopulationGrowthService {

    @Autowired
    private ZipcodeToCbsaRepository zipcodeToCbsaRepository;

    @Autowired
    private CbsaToMsaRepository cbsaToMsaRepository;

    @Autowired
    private PopulationGrowthMapper populationGrowthMapper;

    public PopulationGrowthService(ZipcodeToCbsaRepository zipcodeToCbsaRepository,
                                   CbsaToMsaRepository cbsaToMsaRepository,
                                   PopulationGrowthMapper populationGrowthMapper) {
        this.zipcodeToCbsaRepository = zipcodeToCbsaRepository;
        this.cbsaToMsaRepository = cbsaToMsaRepository;
        this.populationGrowthMapper = populationGrowthMapper;
    }

    static final private String METROPOLITAN_STATISTICAL_AREA = "Metropolitan Statistical Area";

    public PopulationGrowth getPopulationGrowth(String zipcode) throws Exception {
        String preliminaryCbsa = getCbsaFromZipcode(zipcode);
        String finalCbsa = getFinalCbsaFromPreliminaryCbsa(preliminaryCbsa);
        CbsaToMsaEntity cbsaToMsaEntity = getCbsaToMsaEntity(finalCbsa, METROPOLITAN_STATISTICAL_AREA);

        return populationGrowthMapper.toPopulationGrowth(zipcode, cbsaToMsaEntity);
    }

    private CbsaToMsaEntity getCbsaToMsaEntity(String finalCbsa, String lsad) {
        CbsaToMsaEntity cbsaToMsaEntity = cbsaToMsaRepository.findMsaByCbsa(finalCbsa, lsad);

        if (cbsaToMsaEntity == null) {
            throw new ResourceNotFoundException("No MSA information for Cbsa " + finalCbsa + ".");
        }

        return cbsaToMsaEntity;
    }

    private String getFinalCbsaFromPreliminaryCbsa(String preliminaryCbsa) {
        String cbsa = cbsaToMsaRepository.findCbsaByMdiv(preliminaryCbsa);

        if (cbsa == null || cbsa.isEmpty()) {
            return preliminaryCbsa;
        }

        return cbsa;
    }

    private String getCbsaFromZipcode(String zipcode) throws Exception {
        String cbsa = zipcodeToCbsaRepository.findCbsaByZipcode(zipcode);

        if (cbsa == null || cbsa == "99999") {
            throw new ResourceNotFoundException("The zipcode (" + zipcode + ") is not part of a CBSA.");
        } else {
            return cbsa;
        }
    }
}
