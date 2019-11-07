package Model;

public class HospitalModel {
    private int id;
   private String name,contatct, address;

    public HospitalModel(int id, String name, String contatct, String address) {
        this.id = id;
        this.name = name;
        this.contatct = contatct;
        this.address = address;
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

    public String getContatct() {
        return contatct;
    }

    public void setContatct(String contatct) {
        this.contatct = contatct;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
