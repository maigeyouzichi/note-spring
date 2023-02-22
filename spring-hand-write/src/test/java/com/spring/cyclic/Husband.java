package com.spring.cyclic;

import java.time.LocalDate;

public class Husband {

    private Wife wife;

    private LocalDate marriageDate;

    private String wifiName;

    @Override
    public String toString() {
        return "Husband{" +
            "marriageDate=" + marriageDate +
            ", wifeName='" + wifiName + '\'' +
            '}';
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    public String queryWife(){
        return "Husband.wife";
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

}
