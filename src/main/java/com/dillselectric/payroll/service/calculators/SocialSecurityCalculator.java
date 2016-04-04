package com.dillselectric.payroll.service.calculators;

import com.dillselectric.contracts.Calculator;
import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SocialSecurityCalculator implements Calculator {
    private static final double SOCIAL_SECURITY_RATE = 0.062;

    @Override
    public Paycheck calculate(Employee employee, double grossPay, Paycheck paycheck) {
        double socialSecurityWithholding = grossPay * SOCIAL_SECURITY_RATE;

        socialSecurityWithholding = new BigDecimal(socialSecurityWithholding).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

        paycheck.setSocialSecurityWithholdingTax(socialSecurityWithholding);
        paycheck.setEmployerSocialSecurityTax(socialSecurityWithholding);

        return paycheck;
    }
}
