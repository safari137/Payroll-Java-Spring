package com.dillselectric.web.controller;

import com.dillselectric.payroll.model.Employee;
import com.dillselectric.payroll.model.Paycheck;
import com.dillselectric.payroll.service.engine.PayrollEngineDriver;
import com.dillselectric.repository.EmployeeRepository;
import com.dillselectric.repository.PaycheckRepository;
import com.dillselectric.web.view_model.EmployeePayrun;
import com.dillselectric.web.view_model.PayRunContainer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PayrollController {
    private EmployeeRepository employeeRepository;
    private PaycheckRepository paycheckRepository;
    private PayrollEngineDriver payrollEngineDriver = new PayrollEngineDriver(new PaycheckRepository());

    public PayrollController() {
        this.employeeRepository = new EmployeeRepository();
        this.paycheckRepository = new PaycheckRepository();
    }

    @RequestMapping("/payroll")
    public String payrollMenu() {
        return "payroll/index";
    }

    @RequestMapping("/payroll/new")
    public String newPayroll(Model model) {
        List<EmployeePayrun> employeePayrunList = EmployeePayrun.convertEmployeeList(employeeRepository.getAll());

        PayRunContainer payRunContainer = new PayRunContainer(employeePayrunList);

        model.addAttribute("payRunContainer", payRunContainer);

        return "payroll/new_payroll";
    }

    @RequestMapping(value = "/payroll", method = RequestMethod.POST)
    public String runPayroll(@ModelAttribute PayRunContainer payRunContainer, ModelMap modelMap) {
        List<Employee> employees = this.convertViewModelToEmployees(payRunContainer);

        List<Paycheck> paychecks = payrollEngineDriver.executePayroll(employees);

        modelMap.put("paychecks", paychecks);

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

        return "payroll/payrun_details";
    }
}
