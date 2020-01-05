package com.peerstreet.coding.challenge.populationgrowthapi.repository;

import com.peerstreet.coding.challenge.populationgrowthapi.model.CbsaToMsaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CbsaToMsaRepository extends JpaRepository<CbsaToMsaEntity, String> {

    @Query(value = "select mdiv from cbsa_to_msa where cbsa = :preliminaryCbsa limit 1", nativeQuery = true)
    String findMdivByCbsa(@Param("preliminaryCbsa") String preliminaryCbsa);

    @Query(value = "select * from cbsa_to_msa where cbsa = :finalCbsa and lsad = :lsad and name is not null and popestimate2014 is not null and popestimate2015 is not null limit 1", nativeQuery = true)
    CbsaToMsaEntity findMsaByCbsa(@Param("finalCbsa") String finalCbsa, @Param("lsad") String lsad);
}
