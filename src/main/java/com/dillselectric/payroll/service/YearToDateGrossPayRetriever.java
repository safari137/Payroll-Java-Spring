package com.dillselectric.payroll.service;

import com.dillselectric.contracts.DataRetriever;
import com.dillselectric.contracts.Repository;
import com.dillselectric.payroll.model.Paycheck;

import java.util.List;
import java.util.stream.Collectors;

public class YearToDateGrossPayRetriever implements DataRetriever {
    private Repository<Paycheck> repository;
    private int currentYear;

    public YearToDateGrossPayRetriever(Repository<Paycheck> repository, int currentYear) {
        this.repository = repository;
        this.currentYear = currentYear;
    }

    @Override
    public double getData(int id) {
        List<Paycheck> paychecks = getDataFromRepository(id);

        return getGrossTotal(paychecks);
    }

    private List<Paycheck> getDataFromRepository(int id) {
        List<Paycheck> paychecks = repository
                .getAll()
                .stream()
                .filter(p -> p.getEmployeeId() == id)
                .filter(p -> p.getDate().YEAR == currentYear)
                .collect(Collectors.toList());

        return paychecks;
    }

    private double getGrossTotal(List<Paycheck> paychecks) {
        double sum = 0;

        for (Paycheck paycheck : paychecks) {
            sum += paycheck.getGrossAmount();
        }

        return sum;
    }
}
