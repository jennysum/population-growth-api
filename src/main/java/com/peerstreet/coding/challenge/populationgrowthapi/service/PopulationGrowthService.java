package com.peerstreet.coding.challenge.populationgrowthapi.service;

import com.peerstreet.coding.challenge.populationgrowthapi.mapper.PopulationGrowthMapper;
import com.peerstreet.coding.challenge.populationgrowthapi.model.CbsaToMsaEntity;
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

    public PopulationGrowth getPopulationGrowth(String zipcode) throws Exception {
        String preliminaryCbsa = getCbsaFromZipcode(zipcode);
        String finalCbsa = getFinalCbsaFromPreliminaryCbsa(preliminaryCbsa);
        CbsaToMsaEntity cbsaToMsaEntity = getCbsaToMsaEntity(finalCbsa);
        return populationGrowthMapper.toPopulationGrowth(zipcode, cbsaToMsaEntity);
    }

    private CbsaToMsaEntity getCbsaToMsaEntity(String finalCbsa) {
        return cbsaToMsaRepository.findMsaByCbsa(finalCbsa);
    }

    private String getFinalCbsaFromPreliminaryCbsa(String preliminaryCbsa) {
        String mdiv = cbsaToMsaRepository.findMdivByCbsa(preliminaryCbsa);

        if (mdiv == null || mdiv.isEmpty()) {
            return preliminaryCbsa;
        }

        return mdiv;
    }

    private String getCbsaFromZipcode(String zipcode) throws Exception {
        String cbsa = zipcodeToCbsaRepository.findCbsaByZipcode(zipcode);

        if(cbsa == "99999") {
            throw new Exception("The zipcode is not part of a CBSA.");
        } else {
            return cbsa;
        }
    }
}
