package com.dillselectric.payroll.service.calculators;

import com.dillselectric.contracts.Calculator;
import com.dillselectric.payroll.model.Paycheck;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SocialSecurityCalculatorTest {
    Paycheck paycheck;
    Calculator calculator;

    @Before
    public void setUp() throws Exception {
        paycheck = new Paycheck();
        calculator = new SocialSecurityCalculator();
    }

    @Test
    public void socialSecurityCalculatorSetsBothPropertiesOfPaycheck() throws Exception {
        paycheck = calculator.calculate(null, 100.00, paycheck);

        assertEquals("social security withholding", 6.2, paycheck.getSocialSecurityWithholdingTax(), 0);
        assertEquals("employer social security withholding", 6.2, paycheck.getEmployerSocialSecurityTax(), 0);
    }

    @Test
    public void socialSecurityCalculatorReturnsSameValueForEmployeeAndEmployer() {
        paycheck = calculator.calculate(null, 100.00, paycheck);

        assertEquals(paycheck.getEmployerSocialSecurityTax(), paycheck.getSocialSecurityWithholdingTax(), 0);
    }

    @Test
    public void socialSecurityCalculatorReturns_0_WhenGrossPayIs_0() throws Exception {
        paycheck = calculator.calculate(null, 0, paycheck);

        assertEquals("social security withholding", 0, paycheck.getSocialSecurityWithholdingTax(), 0);
        assertEquals("employer social security withholding", 0, paycheck.getEmployerSocialSecurityTax(), 0);
    }

    @Test
    public void socialSecurityCalculatorRoundsDigitsCorrectly() throws Exception {
        paycheck = calculator.calculate(null, 137.00, paycheck);

        assertEquals("social security withholding", 8.49, paycheck.getSocialSecurityWithholdingTax(), 0);
        assertEquals("employer social security withholding", 8.49, paycheck.getEmployerSocialSecurityTax(), 0);
    }
}