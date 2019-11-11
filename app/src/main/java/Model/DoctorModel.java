package Model;

public class DoctorModel {
    private  int id;
    private String name, spciality;

    public DoctorModel(String name, String spciality) {
        this.name = name;
        this.spciality = spciality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpciality() {
        return spciality;
    }

    public void setSpciality(String spciality) {
        this.spciality = spciality;
    }
}
