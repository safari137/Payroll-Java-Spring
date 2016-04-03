package com.dillselectric.payroll.service.engine;

import com.dillselectric.contracts.Calculator;
import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;
import com.dillselectric.payroll.service.calculators.GrossCalculator;

import java.util.List;

public class PayrollEngine {
    private List<Calculator> calculators;
    private GrossCalculator grossCalculator = new GrossCalculator();

    public PayrollEngine(List<Calculator> calculators) {
        this.calculators = calculators;
    }

    public Paycheck processPay(Employee employee, double hours) {
        double grossWage = grossCalculator.Calculate(employee.getPayRate(), hours);

        Paycheck paycheck = new Paycheck();
        paycheck.setGrossAmount(grossWage);

        for (Calculator calculator : calculators) {
            paycheck = calculator.calculate(employee, grossWage, paycheck);
        }

        return paycheck;
    }
}
