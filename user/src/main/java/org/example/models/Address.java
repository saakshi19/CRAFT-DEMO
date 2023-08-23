package org.example.models;

public class Address {
    private int id;
    private String pincode;
    private String state;
    private String city;
    private String line1;
    private String line2;

    public Address(int id, String pincode, String state, String city, String line1, String line2) {
        this.id = id;
        this.pincode = pincode;
        this.state = state;
        this.city = city;
        this.line1 = line1;
        this.line2 = line2;
    }
}
