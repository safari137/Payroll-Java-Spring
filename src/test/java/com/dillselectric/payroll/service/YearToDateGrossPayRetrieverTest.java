package com.dillselectric.payroll.service;

import com.dillselectric.contracts.Repository;
import com.dillselectric.payroll.model.Paycheck;
import org.junit.Before;
import org.junit.Test;
import sun.util.calendar.CalendarDate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;


public class YearToDateGrossPayRetrieverTest {
    YearToDateGrossPayRetriever dataRetriever;

    @Before
    public void setUp() throws Exception {
        dataRetriever = new YearToDateGrossPayRetriever(new DummyRepository(), 2016);
    }

    @Test
    public void dataRetrieverOnlyGetsDataFromOneEmployee() throws Exception {
        double gross = dataRetriever.getData(1);

        assertEquals(450, gross, 0);
    }

    @Test
    public void dataRetrieverOnlyGetsDataFromSpecifiedYear() throws Exception {
        double gross = dataRetriever.getData(2);

        assertEquals(200, gross, 0);
    }

    public class DummyRepository implements Repository<Paycheck> {
        private final List<Paycheck> PAYCHECKS = getFakeData();

        @Override
        public List<Paycheck> getAll() {
            return PAYCHECKS;
        }

        private List<Paycheck> getFakeData() {
            Calendar date = Calendar.getInstance();
            date.set(Calendar.YEAR, 2016);

            Calendar date2 = Calendar.getInstance();
            date2.set(Calendar.YEAR, 2017);

            Paycheck paycheck1 = new Paycheck();
            paycheck1.setDate(date);
            paycheck1.setEmployeeId(1);
            paycheck1.setGrossAmount(150);

            Paycheck paycheck2 = new Paycheck();
            paycheck2.setDate(date);
            paycheck2.setEmployeeId(1);
            paycheck2.setGrossAmount(300);

            Paycheck paycheck3 = new Paycheck();
            paycheck3.setDate(date);
            paycheck3.setEmployeeId(2);
            paycheck3.setGrossAmount(100);

            Paycheck paycheck4 = new Paycheck();
            paycheck4.setDate(date);
            paycheck4.setEmployeeId(2);
            paycheck4.setGrossAmount(100);

            Paycheck paycheck5 = new Paycheck();
            paycheck5.setDate(date2);
            paycheck5.setEmployeeId(2);
            paycheck5.setGrossAmount(100);

            return Arrays.asList(
                    paycheck1,
                    paycheck2,
                    paycheck3,
                    paycheck4,
                    paycheck5);
        }

        @Override
        public void insert(Paycheck item) {

        }

        @Override
        public void update(Paycheck item) {

        }

        @Override
        public Paycheck findById(int id) {
            return null;
        }

        @Override
        public void delete(int id) {

        }
    }

}