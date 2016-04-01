package com.dillselectric.repository;

import com.dillselectric.contracts.Repository;
import com.dillselectric.payroll.model.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EmployeeRepositoryTest {
    private Repository<Employee> repository;

    @Before
    public void setUp() throws Exception {
        repository = new EmployeeRepository();
    }

    @Test
    public void getAllReturnsAllEmployees() throws Exception {
        List<Employee> employees = repository.getAll();

        int numberOfExpectedEmployees = 2;

        assertEquals(numberOfExpectedEmployees, employees.size());
    }

    @Test
    public void updateChangesWork() throws Exception {
        Employee employee = new Employee(1, "Doby", "Dole");
        employee.setEmail("mike@mike.com");
        employee.setPhoneNumber("434-509-2986");
        employee.setPayRate(13.00);

        repository.update(employee);
        Employee updatedEmployee = repository.findById(1);

        assertEquals("first name", "Doby", updatedEmployee.getFirstName());
        assertEquals("last name", "Dole", updatedEmployee.getLastName());
        assertEquals("email", "mike@mike.com", updatedEmployee.getEmail());
        assertEquals("phone number", "434-509-2986", updatedEmployee.getPhoneNumber());
        assertEquals("payRate", 13.00, updatedEmployee.getPayRate(), 0);
    }

    @Test
    public void insertAddsNewEmployee() throws Exception {
        Employee employee = new Employee(3, "Doby", "Dill");

        repository.insert(employee);
        Employee insertedEmployee = repository.findById(3);

        assertEquals("firstName", "Doby", insertedEmployee.getFirstName());
        assertEquals("lastName", "Dill", insertedEmployee.getLastName());
        assertEquals("id", 3, insertedEmployee.getId());
    }

    @Test
    public void deleteRemovesEmployeeFromRepository() throws Exception {
        int numberOfEmployeesInRepositoryBeforeDelete = repository.getAll().size();
        int idOfFirstEmployee = repository.getAll().get(0).getId();

        repository.delete(idOfFirstEmployee);
        int numberOfEmployeesInRepositoryAfterDelete = repository.getAll().size();

        assertEquals(numberOfEmployeesInRepositoryBeforeDelete - 1, numberOfEmployeesInRepositoryAfterDelete);
    }
}