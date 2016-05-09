package com.dillselectric.web.controller;

import com.dillselectric.contracts.Repository;
import com.dillselectric.data.PayrollDao;
import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;
import com.dillselectric.payroll.service.engine.PayrollEngineDriver;
import com.dillselectric.web.view_model.EmployeePayrun;
import com.dillselectric.web.view_model.PayRunContainer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PayrollController {
    private Repository<Employee> employeeRepository = new PayrollDao<>(Employee.class);
    private Repository<Paycheck> paycheckRepository = new PayrollDao<>(Paycheck.class);
    private PayrollEngineDriver payrollEngineDriver = new PayrollEngineDriver(new PayrollDao<Paycheck>(Paycheck.class));

    public PayrollController() {
    }

    @RequestMapping("/payroll")
    public String payrollMenu(ModelMap modelMap) {
        modelMap.put("page", "payroll");

        return "payroll/index";
    }

    @RequestMapping("/payroll/new")
    public String newPayroll(ModelMap modelMap) {
        List<EmployeePayrun> employeePayrunList = EmployeePayrun.convertEmployeeList(employeeRepository.getAll());

        PayRunContainer payRunContainer = new PayRunContainer(employeePayrunList);

        modelMap.put("payRunContainer", payRunContainer);
        modelMap.put("page", "payroll");

        return "payroll/new_payroll";
    }

    @RequestMapping(value = "/payroll", method = RequestMethod.POST)
    public String runPayroll(@ModelAttribute PayRunContainer payRunContainer, ModelMap modelMap) {
        List<Employee> employees = this.convertViewModelToEmployees(payRunContainer);

        List<Paycheck> paychecks = payrollEngineDriver.executePayroll(employees);

        modelMap.put("paychecks", paychecks);
        modelMap.put("page", "payroll");

        return "payroll/payrun_details";
    }

    private List<Employee> convertViewModelToEmployees(PayRunContainer payRunContainer) {
        List<Employee> employees = new ArrayList<>();

        for (EmployeePayrun payrun : payRunContainer.getEmployeePayruns()) {
            if (!payrun.getShouldPay())
                continue;

            Employee employee = employeeRepository.findById(payrun.getEmployeeId());
            employee.setCurrentHours(payrun.getHours());

            employees.add(employee);
        }
        return employees;
    }

    @RequestMapping("/payroll/paychecks")
    public String viewPaychecks(ModelMap modelMap) {
        List<Paycheck> paychecks = paycheckRepository.getAll();

        modelMap.put("paychecks", paychecks);
        modelMap.put("page", "payroll");

        return "payroll/payrun_details";
    }
}
