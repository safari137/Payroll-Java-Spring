package com.dillselectric.payroll.service.calculators;


import com.dillselectric.contracts.Calculator;
import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;

public class VirginiaStateTaxCalculator implements Calculator {

    @Override
    public Paycheck calculate(Employee employee, double grossPay, Paycheck paycheck) {
        return null;
    }
}
