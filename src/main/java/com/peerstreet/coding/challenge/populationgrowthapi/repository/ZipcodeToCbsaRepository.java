package com.peerstreet.coding.challenge.populationgrowthapi.repository;

import com.peerstreet.coding.challenge.populationgrowthapi.model.entity.ZipcodeToCbsaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipcodeToCbsaRepository extends JpaRepository<ZipcodeToCbsaEntity, String> {

    @Query(value = "select cbsa from zipcode_to_cbsa where zip = :zipcode limit 1", nativeQuery = true)
    String findCbsaByZipcode(@Param("zipcode") String zipcode);
}
