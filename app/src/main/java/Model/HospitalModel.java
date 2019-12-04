package Model;

public class HospitalModel {
    private int HOSPITAL_ID;
   private String NAME,CONTACT, ADDRESS,IMAGE,WEBSITE,GENERAL,ICU,EMERGENCY;

    public int getHOSPITAL_ID() {
        return HOSPITAL_ID;
    }

    public void setHOSPITAL_ID(int HOSPITAL_ID) {
        this.HOSPITAL_ID = HOSPITAL_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getCONTACT() {
        return CONTACT;
    }

    public void setCONTACT(String CONTACT) {
        this.CONTACT = CONTACT;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    public String getWEBSITE() {
        return WEBSITE;
    }

    public void setWEBSITE(String WEBSITE) {
        this.WEBSITE = WEBSITE;
    }

    public String getGENERAL() {
        return GENERAL;
    }

    public void setGENERAL(String GENERAL) {
        this.GENERAL = GENERAL;
    }

    public String getICU() {
        return ICU;
    }

    public void setICU(String ICU) {
        this.ICU = ICU;
    }

    public String getEMERGENCY() {
        return EMERGENCY;
    }

    public void setEMERGENCY(String EMERGENCY) {
        this.EMERGENCY = EMERGENCY;
    }
}
