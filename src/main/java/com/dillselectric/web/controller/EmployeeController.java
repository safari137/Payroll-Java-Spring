package com.dillselectric.web.controller;

import com.dillselectric.contracts.Repository;
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

import java.util.List;

@Controller
public class EmployeeController {
    private Repository<Employee> employeeRepository = new EmployeeRepository();

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String allEmployees(ModelMap modelMap) {
        List<Employee> employees = employeeRepository.getAll();

        modelMap.put("employees", employees);

        return "employee/employees";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ModelAndView addEmployee(@ModelAttribute Employee employee) {
        employeeRepository.insert(employee);

        return new ModelAndView("redirect:/employee");
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public ModelAndView editEmployee(@ModelAttribute Employee employee) {
        employeeRepository.update(employee);

        return new ModelAndView("redirect:/employee");
    }

    @RequestMapping("/employee/new")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new Employee());

        return "employee/new";
    }

    @RequestMapping(value = "/employee/{id}")
    public String employeeEdit(@PathVariable int id, ModelMap modelMap) {
        Employee employee = employeeRepository.findById(id);

        modelMap.put("employee", employee);

        return "employee/employee_edit";
    }
}
