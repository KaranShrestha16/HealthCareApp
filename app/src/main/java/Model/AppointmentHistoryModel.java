package Model;

public class AppointmentHistoryModel {
    private String HOSPITAL_NAME,DOCTOR_NAME, APPOINTMENT_DATE, QUALIFICATION, IMAGE, CURRENT_SYMPTOMS;

    public String getHOSPITAL_NAME() {
        return HOSPITAL_NAME;
    }

    public void setHOSPITAL_NAME(String HOSPITAL_NAME) {
        this.HOSPITAL_NAME = HOSPITAL_NAME;
    }

    public String getDOCTOR_NAME() {
        return DOCTOR_NAME;
    }

    public void setDOCTOR_NAME(String DOCTOR_NAME) {
        this.DOCTOR_NAME = DOCTOR_NAME;
    }

    public String getAPPOINTMENT_DATE() {
        return APPOINTMENT_DATE;
    }

    public void setAPPOINTMENT_DATE(String APPOINTMENT_DATE) {
        this.APPOINTMENT_DATE = APPOINTMENT_DATE;
    }

    public String getQUALIFICATION() {
        return QUALIFICATION;
    }

    public void setQUALIFICATION(String QUALIFICATION) {
        this.QUALIFICATION = QUALIFICATION;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    public String getCURRENT_SYMPTOMS() {
        return CURRENT_SYMPTOMS;
    }

    public void setCURRENT_SYMPTOMS(String CURRENT_SYMPTOMS) {
        this.CURRENT_SYMPTOMS = CURRENT_SYMPTOMS;
    }
}
