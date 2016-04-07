package com.dillselectric.payroll.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Paycheck {
    private int id;
    private Calendar date;
    private double grossAmount;
    private double netAmount;
    private double federalWithholdingTax;
    private double stateWithholdingTax;
    private double medicareWithholdingTax;
    private double socialSecurityWithholdingTax;
    private double employerMedicareTax;
    private double employerSocialSecurityTax;
    private double employerFederalUnemploymentTax;
    private double employerStateUnemploymentTax;
    private int employeeId;
    private Employee employee;

    public Paycheck() {
        Random random = new Random();

        this.id = random.nextInt(1000) + 1;
    }

    public double getFederalWithholdingTax() {
        return federalWithholdingTax;
    }

    public void setFederalWithholdingTax(double federalWithholdingTax) {
        this.federalWithholdingTax = federalWithholdingTax;
    }

    public double getStateWithholdingTax() {
        return stateWithholdingTax;
    }

    public void setStateWithholdingTax(double stateWithholdingTax) {
        this.stateWithholdingTax = stateWithholdingTax;
    }

    public double getMedicareWithholdingTax() {
        return medicareWithholdingTax;
    }

    public void setMedicareWithholdingTax(double medicareWithholdingTax) {
        this.medicareWithholdingTax = medicareWithholdingTax;
    }

    public double getSocialSecurityWithholdingTax() {
        return socialSecurityWithholdingTax;
    }

    public void setSocialSecurityWithholdingTax(double socialSecurityWithholdingTax) {
        this.socialSecurityWithholdingTax = socialSecurityWithholdingTax;
    }

    public double getEmployerMedicareTax() {
        return employerMedicareTax;
    }

    public void setEmployerMedicareTax(double employerMedicareTax) {
        this.employerMedicareTax = employerMedicareTax;
    }

    public double getEmployerSocialSecurityTax() {
        return employerSocialSecurityTax;
    }

    public void setEmployerSocialSecurityTax(double employerSocialSecurityTax) {
        this.employerSocialSecurityTax = employerSocialSecurityTax;
    }

    public double getEmployerFederalUnemploymentTax() {
        return employerFederalUnemploymentTax;
    }

    public void setEmployerFederalUnemploymentTax(double employerFederalUnemploymentTax) {
        this.employerFederalUnemploymentTax = employerFederalUnemploymentTax;
    }

    public double getEmployerStateUnemploymentTax() {
        return employerStateUnemploymentTax;
    }

    public void setEmployerStateUnemploymentTax(double employerStateUnemploymentTax) {
        this.employerStateUnemploymentTax = employerStateUnemploymentTax;
    }

    public double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(double grossAmount) {
        this.grossAmount = grossAmount;
    }
    public double getNetAmount() {
        return grossAmount - federalWithholdingTax - stateWithholdingTax - medicareWithholdingTax - socialSecurityWithholdingTax;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
