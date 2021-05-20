package com.thesis.admin;

public class Admins {
    private String AdminID;

    public Admins() {
    }

    public Admins(String adminID, String adminName) {
        AdminID = adminID;
        AdminName = adminName;
    }

    public String getAdminID() {
        return AdminID;
    }

    public void setAdminID(String adminID) {
        AdminID = adminID;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }

    private String AdminName;

}
