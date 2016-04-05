package com.dillselectric.payroll.service.calculators;

import com.dillselectric.contracts.StateGrossCalculator;
import com.dillselectric.payroll.model.Employee;

public class AdjustedStateGrossCalculator implements StateGrossCalculator {
    @Override
    public double calculate(double gross, Employee employee, int payPeriods) {
        return 0;
    }
}
