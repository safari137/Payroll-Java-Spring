package com.dillselectric.web.view_model;

import java.util.List;

public class PayRunContainer {
    private List<EmployeePayrun> employeePayruns;

    public PayRunContainer() {
    }

    public PayRunContainer(List<EmployeePayrun> employeePayruns) {
        this.employeePayruns = employeePayruns;
    }

    public List<EmployeePayrun> getEmployeePayruns() {
        return employeePayruns;
    }

    public void setEmployeePayruns(List<EmployeePayrun> employeePayruns) {
        this.employeePayruns = employeePayruns;
    }
}
