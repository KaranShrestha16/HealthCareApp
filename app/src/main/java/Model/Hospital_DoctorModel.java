package Model;

public class Hospital_DoctorModel {
    private int HOSPITAL_ID,DOCTOR_HOSPITAL_ID,DOCTOR_ID;
    private String DEPARTMENT;

    public int getHOSPITAL_ID() {
        return HOSPITAL_ID;
    }

    public void setHOSPITAL_ID(int HOSPITAL_ID) {
        this.HOSPITAL_ID = HOSPITAL_ID;
    }

    public int getDOCTOR_HOSPITAL_ID() {
        return DOCTOR_HOSPITAL_ID;
    }

    public void setDOCTOR_HOSPITAL_ID(int DOCTOR_HOSPITAL_ID) {
        this.DOCTOR_HOSPITAL_ID = DOCTOR_HOSPITAL_ID;
    }

    public int getDOCTOR_ID() {
        return DOCTOR_ID;
    }

    public void setDOCTOR_ID(int DOCTOR_ID) {
        this.DOCTOR_ID = DOCTOR_ID;
    }

    public String getDEPARTMENT() {
        return DEPARTMENT;
    }

    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT = DEPARTMENT;
    }
}
