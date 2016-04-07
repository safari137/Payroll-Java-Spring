package com.dillselectric.web.controller;

import com.dillselectric.repository.EmployeeRepository;
import com.dillselectric.web.view_model.EmployeePayrun;
import com.dillselectric.web.view_model.PayRunContainer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class PayrollController {
    private EmployeeRepository employeeRepository;

    public PayrollController() {
        this.employeeRepository = new EmployeeRepository();
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
    public ModelAndView runPayroll(@ModelAttribute PayRunContainer payRunContainer) {
        for (EmployeePayrun employee : payRunContainer.getEmployeePayruns()) {
            System.out.println(employee.toString());
        }

        return new ModelAndView("redirect:/payroll");
    }
}
