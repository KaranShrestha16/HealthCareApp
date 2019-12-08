package Model;

public class AppointmentModel {
    private int APPOINTMENT_ID,DOCTOR_ID,HOSPITAL_ID,PATIENT_ID;
    private String CURRENT_SYMPTOMS,APPOINTMENT_DATE;

    public int getAPPOINTMENT_ID() {
        return APPOINTMENT_ID;
    }

    public void setAPPOINTMENT_ID(int APPOINTMENT_ID) {
        this.APPOINTMENT_ID = APPOINTMENT_ID;
    }

    public int getDOCTOR_ID() {
        return DOCTOR_ID;
    }

    public void setDOCTOR_ID(int DOCTOR_ID) {
        this.DOCTOR_ID = DOCTOR_ID;
    }

    public int getHOSPITAL_ID() {
        return HOSPITAL_ID;
    }

    public void setHOSPITAL_ID(int HOSPITAL_ID) {
        this.HOSPITAL_ID = HOSPITAL_ID;
    }

    public int getPATIENT_ID() {
        return PATIENT_ID;
    }

    public void setPATIENT_ID(int PATIENT_ID) {
        this.PATIENT_ID = PATIENT_ID;
    }

    public String getCURRENT_SYMPTOMS() {
        return CURRENT_SYMPTOMS;
    }

    public void setCURRENT_SYMPTOMS(String CURRENT_SYMPTOMS) {
        this.CURRENT_SYMPTOMS = CURRENT_SYMPTOMS;
    }

    public String getAPPOINTMENT_DATE() {
        return APPOINTMENT_DATE;
    }

    public void setAPPOINTMENT_DATE(String APPOINTMENT_DATE) {
        this.APPOINTMENT_DATE = APPOINTMENT_DATE;
    }
}
