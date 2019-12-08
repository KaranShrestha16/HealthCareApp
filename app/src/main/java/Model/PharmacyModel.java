package Model;

public class PharmacyModel {
    private int PHARMACY_ID;
    private String NAME, ADDRESS, CONTACT, WEBSITE;

    public int getPHARMACY_ID() {
        return PHARMACY_ID;
    }

    public void setPHARMACY_ID(int PHARMACY_ID) {
        this.PHARMACY_ID = PHARMACY_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getCONTACT() {
        return CONTACT;
    }

    public void setCONTACT(String CONTACT) {
        this.CONTACT = CONTACT;
    }

    public String getWEBSITE() {
        return WEBSITE;
    }

    public void setWEBSITE(String WEBSITE) {
        this.WEBSITE = WEBSITE;
    }
}
