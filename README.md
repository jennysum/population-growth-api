# Population Growth Api

Population Growth Api is a mini api that gets population growth information with a valid zipcode.

## Public API URL
https://secret-sands-06474.herokuapp.com/v1/zipcode/{zipcode}/populationGrowth

Replace {zipcode} with valid 5-digit zip.

## Run Application Locally

```bash
./gradlew bootRun
```

## Run the Unit Tests

```bash
./gradlew clean build
```

## How Input Files Were Imported
1. Created AWS PostGres rds.
2. Created tables with the following scripts:
```sql
create table zipcode_to_cbsa (
	ZIP text not null,
	CBSA text not null,
	RES_RATIO text null,
	BUS_RATIO text null,
	OTH_RATIO text null,
	TOT_RATIO text null
);

create table cbsa_to_msa (
        CBSA text null,
        MDIV text null,
        STCOU text null,
        NAME text null,
        LSAD text null,
        CENSUS2010POP text null,
        ESTIMATEBASE2010 text null,
        POPESTIMATE2010 text null,
        POPESTIMATE2011 text null,
        POPESTIMATE2012 text null,
        POPESTIMATE2013 text null,
        POPESTIMATE2014 text null,
        POPESTIMATE2015 text null,
        NPOPCHG2010 text null,
        NPOPCHG2011 text null,
        NPOPCHG2012 text null,
        NPOPCHG2013 text null,
        NPOPCHG2014 text null,
        NPOPCHG2015 text null,
        BIRTHS2010 text null,
        BIRTHS2011 text null,
        BIRTHS2012 text null,
        BIRTHS2013 text null,
        BIRTHS2014 text null,
        BIRTHS2015 text null,
        DEATHS2010 text null,
        DEATHS2011 text null,
        DEATHS2012 text null,
        DEATHS2013 text null,
        DEATHS2014 text null,
        DEATHS2015 text null,
        NATURALINC2010 text null,
        NATURALINC2011 text null,
        NATURALINC2012 text null,
        NATURALINC2013 text null,
        NATURALINC2014 text null,
        NATURALINC2015 text null,
        INTERNATIONALMIG2010 text null,
        INTERNATIONALMIG2011 text null,
        INTERNATIONALMIG2012 text null,
        INTERNATIONALMIG2013 text null,
        INTERNATIONALMIG2014 text null,
        INTERNATIONALMIG2015 text null,
        DOMESTICMIG2010 text null,
        DOMESTICMIG2011 text null,
        DOMESTICMIG2012 text null,
        DOMESTICMIG2013 text null,
        DOMESTICMIG2014 text null,
        DOMESTICMIG2015 text null,
        NETMIG2010 text null,
        NETMIG2011 text null,
        NETMIG2012 text null,
        NETMIG2013 text null,
        NETMIG2014 text null,
        NETMIG2015 text null,
        RESIDUAL2010 text null,
        RESIDUAL2011 text null,
        RESIDUAL2012 text null,
        RESIDUAL2013 text null,
        RESIDUAL2014 text null,
        RESIDUAL2015 text null);
```
3. Copied CSV files to tables with the following scripts:
```psql
psql -h peerstreet-hud-census-rds.c8g7ic7jlopt.us-west-1.rds.amazonaws.com -U masterUsername -d myDatabase -c "\copy zipcode_to_cbsa FROM '/Users/jennysum/Downloads/zip_to_cbsa.csv' with (format csv,header true, delimiter ',');"

psql -h peerstreet-hud-census-rds.c8g7ic7jlopt.us-west-1.rds.amazonaws.com -U masterUsername -d myDatabase -c "\copy cbsa_to_msa FROM '/Users/jennysum/Downloads/cbsa_to_msa.csv' with (format csv,header true, delimiter ',', encoding 'windows-1251');"
```
4. Dropped columns that were not needed with the following scripts:
```sql
alter table zipcode_to_cbsa drop column RES_RATIO, drop column BUS_RATIO, drop column OTH_RATIO, drop column TOT_RATIO;

alter table cbsa_to_msa drop column STCOU, drop column CENSUS2010POP, drop column ESTIMATEBASE2010, drop column POPESTIMATE2010, drop column POPESTIMATE2011, drop column POPESTIMATE2012, drop column POPESTIMATE2013, drop column NPOPCHG2010, drop column NPOPCHG2011, drop column NPOPCHG2012, drop column NPOPCHG2013, drop column NPOPCHG2014, drop column NPOPCHG2015, drop column BIRTHS2010, drop column BIRTHS2011, drop column BIRTHS2012, drop column BIRTHS2013, drop column BIRTHS2014, drop column BIRTHS2015, drop column DEATHS2010, drop column DEATHS2011, drop column DEATHS2012, drop column DEATHS2013, drop column DEATHS2014, drop column DEATHS2015, drop column NATURALINC2010, drop column NATURALINC2011, drop column NATURALINC2012, drop column NATURALINC2013, drop column NATURALINC2014, drop column NATURALINC2015, drop column INTERNATIONALMIG2010, drop column INTERNATIONALMIG2011, drop column INTERNATIONALMIG2012, drop column INTERNATIONALMIG2013, drop column INTERNATIONALMIG2014, drop column INTERNATIONALMIG2015, drop column DOMESTICMIG2010, drop column DOMESTICMIG2011, drop column DOMESTICMIG2012, drop column DOMESTICMIG2013, drop column DOMESTICMIG2014, drop column DOMESTICMIG2015, drop column NETMIG2010, drop column NETMIG2011, drop column NETMIG2012, drop column NETMIG2013, drop column NETMIG2014, drop column NETMIG2015, drop column RESIDUAL2010, drop column RESIDUAL2011, drop column RESIDUAL2012, drop column RESIDUAL2013, drop column RESIDUAL2014, drop column RESIDUAL2015;
```

## If Time Permitted
- Create PUT to update zipcode_to_cbsa table with link to new csv.
- Create PUT to update cbsa_to_msa table with link to new csv.
- Create higher level API test.
- Create PACT test between client (consumer side) and api (provider side).