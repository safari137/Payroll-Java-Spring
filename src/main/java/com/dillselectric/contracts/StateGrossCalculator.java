package com.dillselectric.contracts;

import com.dillselectric.payroll.model.Employee;

public interface StateGrossCalculator {
    double calculate(double gross, Employee employee, int payPeriods);
}
