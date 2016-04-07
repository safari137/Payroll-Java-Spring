package com.dillselectric.web.view_model;

import com.dillselectric.payroll.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeePayrun {
    private boolean shouldPay;
    public String fullName;
    private int employeeId;
    private double hours;

    public EmployeePayrun() {
        System.out.println("constructor called");
    }

    public EmployeePayrun(int id, String fullName) {
        this.employeeId = id;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "EmployeePayrun{" +
                "shouldPay=" + shouldPay +
                ", fullName='" + fullName + '\'' +
                ", employeeId=" + employeeId +
                ", hours=" + hours +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean getShouldPay() {
        return shouldPay;
    }

    public void setShouldPay(boolean shouldPay) {
        this.shouldPay = shouldPay;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public static List<EmployeePayrun> convertEmployeeList(List<Employee> employeeList) {
        List<EmployeePayrun> payruns = new ArrayList<>();

        for (Employee employee : employeeList) {
            EmployeePayrun payrun = new EmployeePayrun(employee.getId(), employee.getFullName());

            payruns.add(payrun);
        }

        return payruns;
    }
}
