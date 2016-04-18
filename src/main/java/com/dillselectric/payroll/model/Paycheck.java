package com.dillselectric.payroll.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Entity
public class Paycheck {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private Calendar date;
    @Column
    private double grossAmount;
    @Column
    private double netAmount;
    @Column
    private double federalWithholdingTax;
    @Column
    private double stateWithholdingTax;
    @Column
    private double medicareWithholdingTax;
    @Column
    private double socialSecurityWithholdingTax;
    @Column
    private double employerMedicareTax;
    @Column
    private double employerSocialSecurityTax;
    @Column
    private double employerFederalUnemploymentTax;
    @Column
    private double employerStateUnemploymentTax;
    @Column
    private int employeeId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;

    public Paycheck() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
        double result = grossAmount - federalWithholdingTax - stateWithholdingTax - medicareWithholdingTax - socialSecurityWithholdingTax;

        return result;
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getFormattedDate() {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

        return format.format(date.getTime());
    }

    public static String toFormattedNumber(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        return decimalFormat.format(number);
    }
}
