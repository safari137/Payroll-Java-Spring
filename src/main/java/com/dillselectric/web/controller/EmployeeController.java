package com.dillselectric.web.controller;

import com.dillselectric.contracts.Repository;
import com.dillselectric.data.PayrollDao;
import com.dillselectric.payroll.model.Employee;
import com.dillselectric.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

@Controller
public class EmployeeController {
    private Repository<Employee> employeeRepository = new PayrollDao<>(Employee.class);

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String allEmployees(ModelMap modelMap) {
        List<Employee> employees = employeeRepository.getAll();

        modelMap.put("page", "employee");
        modelMap.put("employees", employees);

        return "employee/employees";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ModelAndView addEmployee(@ModelAttribute Employee employee) {
        employeeRepository.insert(employee);

        return new ModelAndView("redirect:/employee");
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public String editEmployee(@ModelAttribute Employee employee, HttpServletRequest request, ModelMap modelMap) {
        employeeRepository.update(employee);

        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping("/employee/new")
    public String newEmployee(ModelMap modelMap) {
        modelMap.put("employee", new Employee());
        modelMap.put("page", "employee");

        return "employee/new";
    }

    @RequestMapping(value = "/employee/{id}/edit")
    public String employeeEditBasic(@PathVariable int id, ModelMap modelMap) {
        Employee employee = employeeRepository.findById(id);

        modelMap.put("employee", employee);
        modelMap.put("page", "employee");

        return "employee/employee_edit_main";
    }

    @RequestMapping("/employee/{id}/edit/contact")
    public String employeeEditContact(@PathVariable int id, HttpServletRequest request, ModelMap modelMap) {
        Employee employee = employeeRepository.findById(id);

        modelMap.put("employee", employee);
        modelMap.put("page", "employee");
        modelMap.put("message", "Saved.");

        return "employee/employee_edit_contact";
    }

    @RequestMapping("/employee/{id}/edit/tax")
    public String employeeEditTax(@PathVariable int id, ModelMap modelMap) {
        Employee employee = employeeRepository.findById(id);

        modelMap.put("employee", employee);
        modelMap.put("page", "employee");

        return "employee/employee_edit_tax";
    }

    @RequestMapping("/employee/{id}/details")
    public String employeeDetails(@PathVariable int id, ModelMap modelMap) {
        Employee employee = employeeRepository.findById(id);

        modelMap.put("employee", employee);
        modelMap.put("page", "employee");

        return "/employee/employee_details";
    }
}
