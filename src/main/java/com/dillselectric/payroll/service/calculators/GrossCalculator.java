package com.dillselectric.payroll.service.calculators;

public class GrossCalculator {
    public double Calculate(double hourlyRate, double hoursWorked) {
        return hourlyRate * hoursWorked;
    }
}
