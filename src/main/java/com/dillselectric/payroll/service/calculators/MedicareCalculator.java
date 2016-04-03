package com.dillselectric.payroll.service.calculators;

import com.dillselectric.contracts.Calculator;
import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MedicareCalculator implements Calculator {
    private static final double MEDICARE_RATE = 0.0765;

    @Override
    public Paycheck Calculate(Employee employee, double grossPay, Paycheck paycheck) {
        double medicareWithholding = grossPay * MEDICARE_RATE;

        medicareWithholding = new BigDecimal(medicareWithholding).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

        paycheck.setMedicareWithholdingTax(medicareWithholding);
        paycheck.setEmployerMedicareTax(medicareWithholding);

        return paycheck;
    }
}
