package com.peerstreet.coding.challenge.populationgrowthapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cbsa_to_msa")
public class CbsaToMsaEntity {

    @Id
    @Column
    private String cbsa;

    @Column
    private String mdiv;

    @Column
    private String name;

    @Column
    private String lsad;

    @Column
    private String popestimate2014;

    @Column
    private String popestimate2015;

    public String getCbsa() {
        return cbsa;
    }

    public String getMdiv() {
        return mdiv;
    }

    public String getName() {
        return name;
    }

    public String getPopestimate2014() {
        return popestimate2014;
    }

    public String getPopestimate2015() {
        return popestimate2015;
    }
}
