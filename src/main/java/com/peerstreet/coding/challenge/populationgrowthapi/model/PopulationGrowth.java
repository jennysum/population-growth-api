package com.peerstreet.coding.challenge.populationgrowthapi.model;


public class PopulationGrowth {
    private String zipcode;
    private String cbsa;
    private String msaName;
    private Population population;

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCbsa(String cbsa) {
        this.cbsa = cbsa;
    }

    public void setMsaName(String msaName) {
        this.msaName = msaName;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCbsa() {
        return cbsa;
    }

    public String getMsaName() {
        return msaName;
    }

    public Population getPopulation() {
        return population;
    }
}
