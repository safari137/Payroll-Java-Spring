package com.dillselectric.payroll.service.calculators;

import com.dillselectric.contracts.Calculator;
import com.dillselectric.contracts.StateGrossCalculator;
import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;
import com.dillselectric.payroll.model.TaxInfo;
import com.dillselectric.payroll.service.brackets.TaxBracketManager;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StateTaxCalculator implements Calculator {
    private static final int PAY_PERIODS_PER_YEAR = 52;

    private TaxBracketManager taxBracketManager;
    private StateGrossCalculator adjustedGrossCalculator;

    public StateTaxCalculator(TaxBracketManager taxBracketManager, StateGrossCalculator adjustedGrossCalculator) {
        this.taxBracketManager = taxBracketManager;
        this.adjustedGrossCalculator = adjustedGrossCalculator;
    }

    @Override
    public Paycheck calculate(Employee employee, double grossPay, Paycheck paycheck) {
        double adjustedGrossPay = adjustedGrossCalculator.calculate(grossPay, employee.getStateExemptions(), PAY_PERIODS_PER_YEAR);

        if (adjustedGrossPay < 0) {
            paycheck.setStateWithholdingTax(0);
            return paycheck;
        }

        TaxInfo info = taxBracketManager.getTaxInfo(adjustedGrossPay);

        double  stateWithholdingTax = info.getBaseTax() + (info.getExcess() * info.getTaxRate());
                stateWithholdingTax /= PAY_PERIODS_PER_YEAR;
                stateWithholdingTax = new BigDecimal(stateWithholdingTax).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

        paycheck.setStateWithholdingTax(stateWithholdingTax);

        return paycheck;
    }
}
