package com.dillselectric.payroll.service.calculators;

import com.dillselectric.contracts.StateGrossCalculator;

public class AdjustedStateGrossCalculator implements StateGrossCalculator {
    @Override
    public double calculate(double gross, int stateExemptions, int payPeriods) {
        return ((gross * payPeriods) - (3000 + (stateExemptions * 930)));
    }
}
