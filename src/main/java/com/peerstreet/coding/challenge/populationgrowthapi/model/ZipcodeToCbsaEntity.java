package com.peerstreet.coding.challenge.populationgrowthapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zipcode_to_cbsa")
public class ZipcodeToCbsaEntity {

    @Id
    @Column(name = "zip")
    private String zipcode;

    @Column
    private String cbsa;

    public String getZipCode() {
        return zipcode;
    }

    public String getCbsa() {
        return cbsa;
    }
}
