package com.dillselectric.payroll.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String dateOfBirth;
    @Column
    private String socialSecurityNumber;

    @Column
    private String phoneNumber;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String zipcode;

    @Column
    private double payRate;
    @Column
    private int federalExemptions;
    @Column
    private int stateExemptions;
    @Column
    private boolean isMarried;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.EAGER)
    private List<Paycheck> paychecks;

    private double currentHours;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public List<Paycheck> getPaychecks() {
        if (paychecks == null) {
            paychecks = new ArrayList<>();
            System.out.println("initialized paychecks list");
        }

        return paychecks;
    }

    public void setPaychecks(List<Paycheck> paychecks) {
        this.paychecks = paychecks;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", payRate=" + payRate +
                ", federalExemptions=" + federalExemptions +
                ", stateExemptions=" + stateExemptions +
                ", isMarried=" + isMarried +
                ", paychecks=" + paychecks +
                ", currentHours=" + currentHours +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        System.out.printf("\n\n\nEquals requested!\n\n\n");

        if (o == null || !(o instanceof Employee)) {
            System.out.printf("\n\n\nNot equal! 1\n\n\n");
            return false;
        }

        Employee other = (Employee) o;

        if (id == other.getId()) {
            System.out.printf("\n\n\nEqual! id = id\n\n\n");
            return true;
        }

        // equivalence by id
        return id == other.getId();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id ^ (id >>> 32));
        return result;
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
