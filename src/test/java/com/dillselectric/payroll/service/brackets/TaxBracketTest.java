package com.dillselectric.payroll.service.brackets;

import com.dillselectric.payroll.model.TaxInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TaxBracketTest {
    private TaxBracket taxBracket;

    @Before
    public void setUp() throws Exception {
        List<TaxInfo> taxInfoList = Arrays.asList(
                new TaxInfo(0, 500, 0, .02),
                new TaxInfo(500, 1000, 100, .05),
                new TaxInfo(1001, 1500, 500, .07)
        );
        taxBracket = new TaxBracket(taxInfoList);
    }

    @Test
    public void taxBracketReturnsCorrectSmallestTaxInfo() throws Exception {
        TaxInfo taxInfo = taxBracket.getTaxInfo(500);

        assertEquals("tax rate", .02, taxInfo.getTaxRate(), 0);
        assertEquals("base tax", 0, taxInfo.getBaseTax(), 0);
        assertEquals("excess", 0, taxInfo.getExcess(), 0);
    }

    @Test
    public void taxBracketReturnsCorrectMidTaxInfo() throws Exception {
        TaxInfo taxInfo = taxBracket.getTaxInfo(500.01);

        assertEquals("tax rate", .05, taxInfo.getTaxRate(), 0);
        assertEquals("base tax", 100, taxInfo.getBaseTax(), 0);
        assertEquals("excess", .01, taxInfo.getExcess(), 0);
    }

    @Test
    public void taxBracketReturnsCorrectLargestTaxInfo() throws Exception {
        TaxInfo taxInfo = taxBracket.getTaxInfo(1001.01);

        assertEquals("tax rate", .07, taxInfo.getTaxRate(), 0);
        assertEquals("base tax", 500, taxInfo.getBaseTax(), 0);
        assertEquals("excess", .01, taxInfo.getExcess(), 0);
    }

}