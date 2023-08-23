package org.example.models;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserProfile {

    private String emailId;
    private String name;
    private List<Address> savedAddresses;
    private String region;

    public String getRegion() {
        return region;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getSavedAddresses() {
        return savedAddresses;
    }

    public void setExpertises(List<Address> savedAddresses) {
        this.savedAddresses = savedAddresses;
    }

}
