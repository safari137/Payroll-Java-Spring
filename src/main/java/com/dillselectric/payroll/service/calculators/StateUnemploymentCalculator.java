package com.dillselectric.payroll.service.calculators;

import com.dillselectric.contracts.Calculator;
import com.dillselectric.contracts.DataRetriever;
import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StateUnemploymentCalculator implements Calculator {
    private static final double STATE_UNEMPLOYMENT_RATE = 0.0292;
    private static final double MAX_TAX_LIABILITY = 8000;

    private DataRetriever dataRetriever;

    public StateUnemploymentCalculator(DataRetriever dataRetriever) {
        this.dataRetriever = dataRetriever;
    }

    @Override
    public Paycheck calculate(Employee employee, double grossPay, Paycheck paycheck) {
        double grossYearToDatePay = dataRetriever.getData(employee.getId());

        boolean hasPaidMaxGross = (grossYearToDatePay >= MAX_TAX_LIABILITY);
        if (hasPaidMaxGross) {
            paycheck.setEmployerStateUnemploymentTax(0);
            return paycheck;
        }

        double amountToPayTaxesOn = MAX_TAX_LIABILITY - grossYearToDatePay;

        double taxDue = (amountToPayTaxesOn >= grossPay) ?
                grossPay * STATE_UNEMPLOYMENT_RATE :
                amountToPayTaxesOn * STATE_UNEMPLOYMENT_RATE;

        taxDue = new BigDecimal(taxDue).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

        paycheck.setEmployerStateUnemploymentTax(taxDue);

        return paycheck;
    }
}
