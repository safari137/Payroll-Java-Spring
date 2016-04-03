package com.dillselectric.payroll.service.calculators;

import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FederalTaxCalculatorTest {
    private Paycheck paycheck;
    private FederalTaxCalculator calculator;
    private Employee employee;

    @Before
    public void setUp() throws Exception {
        paycheck = new Paycheck();
        calculator = new FederalTaxCalculator();
        employee = new Employee();
    }

    @Test
    public void marriedIncomeOver_9144_CalculatesCorrectly() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(true);

        paycheck = calculator.calculate(employee, 9145, paycheck);

        assertEquals(2511.46, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void marriedIncomeOver_8113_CalculatesCorrectly() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(true);

        paycheck = calculator.calculate(employee, 8120, paycheck);

        assertEquals(2152.66, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void marriedIncomeOver_4615_CalculatesCorrectly() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(true);

        paycheck = calculator.calculate(employee, 4620, paycheck);

        assertEquals(997.52, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void marriedIncomeOver_3086_CalculatesCorrectly() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(true);

        paycheck = calculator.calculate(employee, 3090, paycheck);

        assertEquals(568.87, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void marriedIncomeOver_1613_CalculatesCorrectly() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(true);

        paycheck = calculator.calculate(employee, 1620, paycheck);

        assertEquals(201.25, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void marriedIncomeOver_521_CalculatesCorrectly() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(true);

        paycheck = calculator.calculate(employee, 530, paycheck);

        assertEquals(37.05, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void marriedIncomeOver_164_CalculatesCorrectly() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(true);

        paycheck = calculator.calculate(employee, 170, paycheck);

        assertEquals(0.60, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void marriedIncomeUnder_164_CalculatesNoTax() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(true);

        paycheck = calculator.calculate(employee, 150, paycheck);

        assertEquals(0, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void singleIncomeOver_8025_CalculatesCorrectly() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(false);

        paycheck = calculator.calculate(employee, 8030, paycheck);

        assertEquals(2319.91, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void singleIncomeOver_7992_CalculatesCorrectly() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(false);

        paycheck = calculator.calculate(employee, 8000, paycheck);

        assertEquals(2309.18, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void singleIncomeOver_3700_CalculatesCorrectly() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(false);

        paycheck = calculator.calculate(employee, 3710, paycheck);

        assertEquals(893.32, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void singleIncomeOver_1796_CalculatesCorrectly() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(false);

        paycheck = calculator.calculate(employee, 1810, paycheck);

        assertEquals(360.82, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void singleIncomeOver_767_CalculatesCorrectly() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(false);

        paycheck = calculator.calculate(employee, 780, paycheck);

        assertEquals(102.90, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void singleIncomeOver_222_CalculatesCorrectly() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(false);

        paycheck = calculator.calculate(employee, 230, paycheck);

        assertEquals(19.1, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void singleIncomeOver_43_CalculatesCorrectly() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(false);

        paycheck = calculator.calculate(employee, 60, paycheck);

        assertEquals(1.7, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void singleIncomeEqualTo_43_Calculates_0() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(false);

        paycheck = calculator.calculate(employee, 43, paycheck);

        assertEquals(0, paycheck.getFederalWithholdingTax(), 0);
    }

    @Test
    public void singleIncomeUnder_43_Calculates_0() throws Exception {
        employee.setFederalExemptions(0);
        employee.setIsMarried(false);

        paycheck = calculator.calculate(employee, 40, paycheck);

        assertEquals(0, paycheck.getFederalWithholdingTax(), 0);
    }
}