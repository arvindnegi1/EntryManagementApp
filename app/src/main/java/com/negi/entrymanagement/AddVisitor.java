package com.negi.entrymanagement;

public class AddVisitor {
    String name,phone,checkin,checkout,hostname,hostid,addressvisited;
        String status;
    public AddVisitor(String name, String phone, String checkin, String checkout, String hostname, String hostid, String addressvisited,String status) {
        this.name = name;
        this.phone = phone;
        this.checkin = checkin;
        this.checkout = checkout;
        this.hostname = hostname;
        this.hostid = hostid;
        this.addressvisited = addressvisited;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getHostid() {
        return hostid;
    }

    public void setHostid(String hostid) {
        this.hostid = hostid;
    }

    public String getAddressvisited() {
        return addressvisited;
    }

    public void setAddressvisited(String addressvisited) {
        this.addressvisited = addressvisited;
    }
}
