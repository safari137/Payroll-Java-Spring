package com.dillselectric.contracts;

public interface StateGrossCalculator {
    double calculate(double gross, int stateExemptions, int payPeriods);
}
