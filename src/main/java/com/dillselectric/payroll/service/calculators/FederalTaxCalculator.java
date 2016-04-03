package com.dillselectric.payroll.service.calculators;

import com.dillselectric.contracts.Calculator;
import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FederalTaxCalculator implements Calculator {
    private static final double EXEMPTION_VALUE = 77.90;

    @Override
    public Paycheck Calculate(Employee employee, double grossPay, Paycheck paycheck) {
        double adjustedGrossPay = grossPay - (employee.getFederalExemptions() * EXEMPTION_VALUE);

        double federalWithholdingTax = (employee.getIsMarried()) ?
                CalculateMarried(adjustedGrossPay) :
                CalculateSingle(adjustedGrossPay);

        paycheck.setFederalWithholdingTax(federalWithholdingTax);

        return paycheck;
    }

    private double CalculateMarried(double adjustedGross) {
        double  fedTaxRate,
                federalWithholding,
                excessPay;

        if (adjustedGross > 9144) {
            fedTaxRate = 0.396;
            federalWithholding = 2511.06;
            excessPay = adjustedGross - 9144;
        }

        else if (adjustedGross > 8113) {
            fedTaxRate = 0.35;
            federalWithholding = 2150.21;
            excessPay = adjustedGross - 8113;
        }

        else if (adjustedGross > 4615) {
            fedTaxRate = 0.33;
            federalWithholding =995.87;
            excessPay = adjustedGross - 4615;
        }

        else if (adjustedGross > 3086) {
            fedTaxRate = .28;
            federalWithholding = 567.75;
            excessPay = adjustedGross - 3086;
        }

        else if (adjustedGross > 1613) {
            fedTaxRate = 0.25;
            federalWithholding = 199.50;
            excessPay = adjustedGross - 1613;
        }

        else if (adjustedGross > 521) {
            fedTaxRate = 0.15;
            federalWithholding = 35.70;
            excessPay = adjustedGross - 521;
        }

        else if (adjustedGross > 164) {
            fedTaxRate = 0.10;
            federalWithholding = 0;
            excessPay = adjustedGross - 164;
        }

        else {
            return 0;
        }

        return getFinalCalculation(federalWithholding, excessPay, fedTaxRate);
    }

    private double CalculateSingle(double adjustedGross) {
        double  fedTaxRate,
                federalWithholding,
                excessPay;

        if (adjustedGross > 8025) {
            fedTaxRate = 0.396;
            federalWithholding = 2317.93;
            excessPay = adjustedGross - 8025;
        }

        else if (adjustedGross > 7992) {
            fedTaxRate = 0.35;
            federalWithholding = 2306.38;
            excessPay = adjustedGross - 7992;
        }

        else if (adjustedGross > 3700) {
            fedTaxRate = 0.33;
            federalWithholding = 890.02;
            excessPay = adjustedGross - 3700;
        }

        else if (adjustedGross > 1796) {
            fedTaxRate = 0.28;
            federalWithholding = 356.90;
            excessPay = adjustedGross - 1796;
        }

        else if (adjustedGross > 767) {
            fedTaxRate = 0.25;
            federalWithholding = 99.65;
            excessPay = adjustedGross - 767;
        }

        else if (adjustedGross > 222) {
            fedTaxRate = 0.15;
            federalWithholding = 17.90;
            excessPay = adjustedGross - 222;
        }

        else if (adjustedGross > 43) {
            fedTaxRate = 0.10;
            federalWithholding = 0;
            excessPay = adjustedGross - 43;
        }

        else {
            return 0;
        }

        return getFinalCalculation(federalWithholding, excessPay, fedTaxRate);
    }

    private double getFinalCalculation(double federalWithholding, double excessPay, double fedTaxRate) {
        federalWithholding += (excessPay > 0) ? (fedTaxRate * excessPay) : 0;
        federalWithholding = new BigDecimal(federalWithholding).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

        return  federalWithholding;
    }
}
