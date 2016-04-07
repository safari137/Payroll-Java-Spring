package com.dillselectric.payroll.model;


import java.util.Random;

public class Employee {
    private int id;

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String socialSecurityNumber;

    private String phoneNumber;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zipcode;

    private double payRate;
    private int federalExemptions;
    private int stateExemptions;
    private boolean isMarried;

    private double currentHours;

    public Employee() {
        Random random = new Random();
        this.id = random.nextInt(1000) + 1;
    }

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public int getFederalExemptions() {
        return federalExemptions;
    }

    public void setFederalExemptions(int federalExemptions) {
        this.federalExemptions = federalExemptions;
    }

    public int getStateExemptions() {
        return stateExemptions;
    }

    public void setStateExemptions(int stateExemptions) {
        this.stateExemptions = stateExemptions;
    }

    public boolean getIsMarried() { return isMarried; }

    public void setIsMarried(boolean married) {
        isMarried = married;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public double getCurrentHours() {
        return currentHours;
    }

    public void setCurrentHours(double currentHours) {
        this.currentHours = currentHours;
    }
}
