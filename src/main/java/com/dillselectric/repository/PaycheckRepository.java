package com.dillselectric.repository;


import com.dillselectric.contracts.Repository;
import com.dillselectric.payroll.model.Paycheck;

import java.util.ArrayList;
import java.util.List;

public class PaycheckRepository implements Repository<Paycheck> {
    private final List<Paycheck> PAYCHECKS =  new ArrayList<>();

    public PaycheckRepository() {
    }

    @Override
    public List<Paycheck> getAll() {
        return PAYCHECKS;
    }

    @Override
    public void insert(Paycheck item) {
        PAYCHECKS.add(item);
    }

    @Override
    public void update(Paycheck item) {
        for(int i = 0; i< PAYCHECKS.size(); i++) {
            if (PAYCHECKS.get(i).getId() == item.getId()) {
                PAYCHECKS.set(i, item);
                return;
            }
        }
    }

    @Override
    public Paycheck findById(int id) {
        for (Paycheck paycheck : PAYCHECKS) {
            if (paycheck.getId() == id)
                return paycheck;
        }
        return null;
    }

    @Override
    public void save() {
        // Not Needed At This Time
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i< PAYCHECKS.size(); i++) {
            if (PAYCHECKS.get(i).getId() == id) {
                PAYCHECKS.remove(i);
                return;
            }
        }
    }
}
