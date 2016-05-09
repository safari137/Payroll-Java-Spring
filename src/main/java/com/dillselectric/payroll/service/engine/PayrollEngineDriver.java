package com.dillselectric.payroll.service.engine;

import com.dillselectric.contracts.Calculator;
import com.dillselectric.contracts.Repository;
import com.dillselectric.data.PayrollDao;
import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;
import com.dillselectric.payroll.model.TaxInfo;
import com.dillselectric.payroll.service.YearToDateGrossPayRetriever;
import com.dillselectric.payroll.service.brackets.TaxBracketManager;
import com.dillselectric.payroll.service.calculators.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class PayrollEngineDriver {
    private List<Calculator> calculatorList;

    private PayrollEngine engine;
    private Repository<Paycheck> paycheckRepository;
    private Repository<Employee> employeeRepository = new PayrollDao<>(Employee.class);

    private Calendar today = Calendar.getInstance();

    public PayrollEngineDriver(Repository<Paycheck> paycheckRepository) {
        this.paycheckRepository = paycheckRepository;
        this.engine = new PayrollEngine(getCalculators());
    }

    public List<Paycheck> executePayroll(List<Employee> employees) {
        List<Paycheck> paychecks = new ArrayList<>();

        for(Employee employee : employees) {
            Paycheck paycheck = engine.processPay(employee, employee.getCurrentHours());
            paycheck.setEmployeeId(employee.getId());
            paycheck.setEmployee(employee);
            paycheck.setDate(today);
            paychecks.add(paycheck);
            employee.setCurrentHours(0);

            employee.getPaychecks().add(paycheck);

            employeeRepository.update(employee);
        }

        return paychecks;
    }

    private List<Calculator> getCalculators() {
        List<TaxInfo> stateTaxBrackets = Arrays.asList(
                new TaxInfo(0, 3000, 0, .02),
                new TaxInfo(3000, 5000, 60, .03),
                new TaxInfo(5000, 17000, 120, .05),
                new TaxInfo(17000, 0, 720, .0575)
        );

        int year = today.YEAR;

        calculatorList = Arrays.asList(
                new FederalTaxCalculator(),
                new StateTaxCalculator(new TaxBracketManager(stateTaxBrackets), new AdjustedStateGrossCalculator()),
                new SocialSecurityCalculator(),
                new MedicareCalculator(),
                new FederalUnemploymentCalculator(new YearToDateGrossPayRetriever(paycheckRepository, year)),
                new StateUnemploymentCalculator(new YearToDateGrossPayRetriever(paycheckRepository, year))
        );

        return calculatorList;
    }
}
