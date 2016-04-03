package com.dillselectric.contracts;

import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;

public interface Calculator {
    Paycheck Calculate(Employee employee, double grossPay, Paycheck paycheck);
}
