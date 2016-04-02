package com.dillselectric.repository;

import com.dillselectric.contracts.Repository;
import com.dillselectric.payroll.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {
    private static final List<Employee> EMPLOYEES =  new ArrayList<>();

    public EmployeeRepository() {
        Employee mike = new Employee(1, "Michael", "Dill");
        mike.setIsMarried(true);
        EMPLOYEES.add(mike);
        EMPLOYEES.add(new Employee(2, "David", "Dill"));
    }

    @Override
    public List<Employee> getAll() {
        return EMPLOYEES;
    }

    @Override
    public void insert(Employee item) {
        EMPLOYEES.add(item);
    }

    @Override
    public void update(Employee item) {
        for (int i = 0; i<EMPLOYEES.size(); i++) {
            if (EMPLOYEES.get(i).getId() == item.getId()) {
                EMPLOYEES.set(i, item);
                return;
            }
        }
    }

    @Override
    public Employee findById(int id) {
        for (Employee employee : EMPLOYEES) {
            if (employee.getId() == id) 
                return employee;
        }
        return null;
    }

    @Override
    public void save() {
        // Not Needed At This Time
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i<EMPLOYEES.size(); i++) {
            if (EMPLOYEES.get(i).getId() == id) {
                EMPLOYEES.remove(i);
                return;
            }
        }
    }
}
