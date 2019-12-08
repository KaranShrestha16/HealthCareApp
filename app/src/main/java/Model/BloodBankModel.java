package Model;

public class BloodBankModel {
    private int BLOOD_BANK_ID;
    private String NAME, ADDRESS, CONTACT;

    public int getBLOOD_BANK_ID() {
        return BLOOD_BANK_ID;
    }

    public void setBLOOD_BANK_ID(int BLOOD_BANK_ID) {
        this.BLOOD_BANK_ID = BLOOD_BANK_ID;
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
}


