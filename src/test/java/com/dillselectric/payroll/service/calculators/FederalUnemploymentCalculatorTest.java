package com.dillselectric.payroll.service.calculators;

import com.dillselectric.contracts.DataRetriever;
import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FederalUnemploymentCalculatorTest {
    private FederalUnemploymentCalculator calculator;
    private Paycheck paycheck;
    private Employee employee;

    @Before
    public void setUp() throws Exception {
        employee = new Employee();
        employee.setId(0);

        paycheck = new Paycheck();
    }

    @Test
    public void calculatorReturnsCorrectPercentage() throws Exception {
        calculator = new FederalUnemploymentCalculator(new DummyDataRetriever(0));

        paycheck = calculator.calculate(employee, 1000, paycheck);

        assertEquals(6, paycheck.getEmployerFederalUnemploymentTax(), 0);
    }

    @Test
    public void calculatorReturns_0_WhenMaxGrossHasBeenPaid() throws Exception {
        double maxGross = 7000;
        calculator = new FederalUnemploymentCalculator(new DummyDataRetriever(maxGross));

        paycheck = calculator.calculate(employee, 1000, paycheck);

        assertEquals(0, paycheck.getEmployerFederalUnemploymentTax(), 0);
    }

    @Test
    public void calculatorReturnsPartialCalculation_WhenGrossPaidIsGreaterThan_PaycheckGross() throws Exception {
        calculator = new FederalUnemploymentCalculator(new DummyDataRetriever(6500));

        paycheck = calculator.calculate(employee, 1000, paycheck);

        assertEquals(3, paycheck.getEmployerFederalUnemploymentTax(), 0);
    }

    public class DummyDataRetriever implements DataRetriever {
        private double gross;

        public DummyDataRetriever(double gross) {
            this.gross = gross;
        }

        @Override
        public double getData(int id) {
            return gross;
        }
    }
}