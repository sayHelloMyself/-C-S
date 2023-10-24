package ThreeCS;

import java.io.Serializable;

public class Contact implements Serializable {
    private int id;
    private String name;
    private String address;
    private String phone;

    public Contact(int id,String name, String address, String phone) {
        this.id=id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public void updateContact(int id,String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Address: " + address + ", Phone: " + phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
