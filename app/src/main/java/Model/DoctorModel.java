package Model;

public class DoctorModel {
    private  int DOCTOR_ID;
    private String DOCTOR_NAME,ADDRESS,CONTACT,GENDER,QUALIFICATION, DOCTOR_IMAGE ;


    public int getDOCTOR_ID() {
        return DOCTOR_ID;
    }

    public void setDOCTOR_ID(int DOCTOR_ID) {
        this.DOCTOR_ID = DOCTOR_ID;
    }

    public String getDOCTOR_NAME() {
        return DOCTOR_NAME;
    }

    public void setDOCTOR_NAME(String DOCTOR_NAME) {
        this.DOCTOR_NAME = DOCTOR_NAME;
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

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getQUALIFICATION() {
        return QUALIFICATION;
    }

    public void setQUALIFICATION(String QUALIFICATION) {
        this.QUALIFICATION = QUALIFICATION;
    }

    public String getDOCTOR_IMAGE() {
        return DOCTOR_IMAGE;
    }

    public void setDOCTOR_IMAGE(String DOCTOR_IMAGE) {
        this.DOCTOR_IMAGE = DOCTOR_IMAGE;
    }
}
