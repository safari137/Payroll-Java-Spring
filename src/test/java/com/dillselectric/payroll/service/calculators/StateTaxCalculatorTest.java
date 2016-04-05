package com.dillselectric.payroll.service.calculators;

import com.dillselectric.contracts.StateGrossCalculator;
import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;
import com.dillselectric.payroll.model.TaxInfo;
import com.dillselectric.payroll.service.brackets.TaxBracketManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StateTaxCalculatorTest {
    private StateTaxCalculator stateTaxCalculator;
    private Employee employee;
    private Paycheck paycheck;

    @Before
    public void setUp() throws Exception {
        List<TaxInfo> taxBrackets = Arrays.asList(
                new TaxInfo(0, 3000, 0, .02),
                new TaxInfo(3000, 5000, 60, .03),
                new TaxInfo(5000, 17000, 120, .05),
                new TaxInfo(17000, 0, 720, .0575)
        );

        TaxBracketManager taxBracketManager = new TaxBracketManager(taxBrackets);

        stateTaxCalculator = new StateTaxCalculator(taxBracketManager, new DummyStateGrossCalculator());

        employee = new Employee();
        employee.setStateExemptions(0);
        paycheck = new Paycheck();
    }

    @Test
    public void stateTaxCalculatorCalculatesSmallestTaxBracket() throws Exception {
        paycheck = stateTaxCalculator.calculate(employee, 3000, paycheck);

        assertEquals(1.15, paycheck.getStateWithholdingTax(), 0);
    }

    @Test
    public void stateTaxCalculatorCalculates_3000_To_5000_TaxBracket() throws Exception {
        paycheck = stateTaxCalculator.calculate(employee, 3500, paycheck);

        assertEquals(1.44, paycheck.getStateWithholdingTax(), 0);
    }

    @Test
    public void stateTaxCalculatorCalculates_5000_To_17000_TaxBracket() throws Exception {
        paycheck = stateTaxCalculator.calculate(employee, 5500, paycheck);

        assertEquals(2.79, paycheck.getStateWithholdingTax(), 0);
    }

    @Test
    public void stateTaxCalculatorCalculates_17000_Plus_TaxBracket() throws Exception {
        paycheck = stateTaxCalculator.calculate(employee, 18000, paycheck);

        assertEquals(14.95, paycheck.getStateWithholdingTax(), 0);
    }

    @Test
    public void stateTaxCalculatorCalculatesReturns_0_WhenGrossIsNegative() throws Exception {
        paycheck = stateTaxCalculator.calculate(employee, -400, paycheck);

        assertEquals(0, paycheck.getStateWithholdingTax(), 0);
    }

    public class DummyStateGrossCalculator implements StateGrossCalculator {
        @Override
        public double calculate(double gross, int stateExemptions, int payPeriods) {
            return gross;
        }
    }
}