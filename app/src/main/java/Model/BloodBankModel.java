package Model;

public class BloodBankModel {
    private int bloodBank_id;
    private String name, address, contact, webpage;

    public BloodBankModel(String name, String address, String contact, String webpage) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.webpage = webpage;
    }

    public int getBloodBank_id() {
        return bloodBank_id;
    }

    public void setBloodBank_id(int bloodBank_id) {
        this.bloodBank_id = bloodBank_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }
}


