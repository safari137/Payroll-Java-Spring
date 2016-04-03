package com.dillselectric.payroll.service.calculators;

import com.dillselectric.contracts.Calculator;
import com.dillselectric.payroll.model.Paycheck;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MedicareCalculatorTest {
    Paycheck paycheck;
    Calculator calculator;

    @Before
    public void setUp() throws Exception {
        paycheck = new Paycheck();
        calculator = new MedicareCalculator();
    }

    @Test
    public void medicareCalculatorSetsBothPropertiesOfPaycheck() throws Exception {
        calculator.Calculate(null, 100.00, paycheck);

        assertEquals("medicare withholding", 7.65, paycheck.getMedicareWithholdingTax(), 0);
        assertEquals("employer medicare withholding", 7.65, paycheck.getEmployerMedicareTax(), 0);
    }

    @Test
    public void medicareCalculatorReturnsSameValueForEmployeeAndEmployer() {
        calculator.Calculate(null, 100.00, paycheck);

        assertEquals(paycheck.getEmployerMedicareTax(), paycheck.getMedicareWithholdingTax(), 0);
    }

    @Test
    public void medicareCalculatorReturns_0_WhenGrossPayIs_0() throws Exception {
        calculator.Calculate(null, 0, paycheck);

        assertEquals("medicare withholding", 0, paycheck.getMedicareWithholdingTax(), 0);
        assertEquals("employer medicare withholding", 0, paycheck.getEmployerMedicareTax(), 0);
    }

    @Test
    public void medicareCalculatorRoundsDigitsCorrectly() throws Exception {
        calculator.Calculate(null, 137.00, paycheck);

        assertEquals("medicare withholding", 10.48, paycheck.getMedicareWithholdingTax(), 0);
        assertEquals("employer medicare withholding", 10.48, paycheck.getEmployerMedicareTax(), 0);
    }
}