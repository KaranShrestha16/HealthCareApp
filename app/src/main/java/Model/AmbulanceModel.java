package Model;

public class AmbulanceModel {

    private int AMBULANCE_ID;
    private String NAME,ADDRESS, CONTACT;

    public int getAMBULANCE_ID() {
        return AMBULANCE_ID;
    }

    public void setAMBULANCE_ID(int AMBULANCE_ID) {
        this.AMBULANCE_ID = AMBULANCE_ID;
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
