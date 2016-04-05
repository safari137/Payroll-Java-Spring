package com.dillselectric.payroll.service.calculators;

import com.dillselectric.contracts.Calculator;
import com.dillselectric.contracts.DataRetriever;
import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FederalUnemploymentCalculator implements Calculator {
    private static final double MAX_GROSS = 7000;
    private static final double FEDERAL_UNEMPLOYMENT_TAX_RATE = 0.006;

    private DataRetriever dataRetriever;

    public FederalUnemploymentCalculator(DataRetriever dataRetriever) {
        this.dataRetriever = dataRetriever;
    }

    @Override
    public Paycheck calculate(Employee employee, double grossPay, Paycheck paycheck) {
        double grossYearToDatePay = dataRetriever.getData(employee.getId());

        boolean hasPaidMaxGross = (grossYearToDatePay >= MAX_GROSS);
        if (hasPaidMaxGross) {
            paycheck.setEmployerFederalUnemploymentTax(0);
            return paycheck;
        }

        double amountToPayTaxesOn = MAX_GROSS - grossYearToDatePay;

        double taxDue = (amountToPayTaxesOn >= grossPay) ?
                grossPay * FEDERAL_UNEMPLOYMENT_TAX_RATE :
                amountToPayTaxesOn * FEDERAL_UNEMPLOYMENT_TAX_RATE;

        taxDue = new BigDecimal(taxDue).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

        paycheck.setEmployerFederalUnemploymentTax(taxDue);

        return paycheck;
    }
}
