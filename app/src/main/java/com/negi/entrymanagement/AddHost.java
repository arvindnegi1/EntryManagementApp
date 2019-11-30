package com.negi.entrymanagement;

public class AddHost {

    String regno, name,phone,email, Designation;

    public AddHost(String regno,String name,String phone,String email,String Designation) {
     this.Designation=Designation;
     this.regno=regno;
     this.name=name;
     this.email=email;
     this.phone=phone;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }
}

