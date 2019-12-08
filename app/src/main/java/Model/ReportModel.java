package Model;

public class ReportModel {

    private int REPORT_ID,PATIENT_ID;
    private String REPORT_NAME,REPORT_IMAGE,REPORT_DATE,DESCRIPTION;

    public int getREPORT_ID() {
        return REPORT_ID;
    }

    public void setREPORT_ID(int REPORT_ID) {
        this.REPORT_ID = REPORT_ID;
    }

    public int getPATIENT_ID() {
        return PATIENT_ID;
    }

    public void setPATIENT_ID(int PATIENT_ID) {
        this.PATIENT_ID = PATIENT_ID;
    }

    public String getREPORT_IMAGE() {
        return REPORT_IMAGE;
    }

    public void setREPORT_IMAGE(String REPORT_IMAGE) {
        this.REPORT_IMAGE = REPORT_IMAGE;
    }

    public String getREPORT_DATE() {
        return REPORT_DATE;
    }

    public void setREPORT_DATE(String REPORT_DATE) {
        this.REPORT_DATE = REPORT_DATE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public String getREPORT_NAME() {
        return REPORT_NAME;
    }

    public void setREPORT_NAME(String REPORT_NAME) {
        this.REPORT_NAME = REPORT_NAME;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;


    }
}
