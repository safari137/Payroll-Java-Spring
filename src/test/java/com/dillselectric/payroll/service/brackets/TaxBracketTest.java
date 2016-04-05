package com.dillselectric.payroll.service.brackets;

import com.dillselectric.payroll.model.TaxInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TaxBracketTest {
    private TaxBracketManager taxBracketManager;

    @Before
    public void setUp() throws Exception {
        List<TaxInfo> taxInfoList = Arrays.asList(
                new TaxInfo(0, 500, 0, .02),
                new TaxInfo(500, 1000, 100, .05),
                new TaxInfo(1001, 1500, 500, .07),
                new TaxInfo(2000, 0, 1000, .10)
        );
        taxBracketManager = new TaxBracketManager(taxInfoList);
    }

    @Test
    public void taxBracketReturnsCorrectSmallestTaxInfo() throws Exception {
        TaxInfo taxInfo = taxBracketManager.getTaxInfo(500);

        assertEquals("tax rate", .02, taxInfo.getTaxRate(), 0);
        assertEquals("base tax", 0, taxInfo.getBaseTax(), 0);
        assertEquals("excess", 500, taxInfo.getExcess(), 0);
    }

    @Test
    public void taxBracketReturnsCorrectMidTaxInfo() throws Exception {
        TaxInfo taxInfo = taxBracketManager.getTaxInfo(500.01);

        assertEquals("tax rate", .05, taxInfo.getTaxRate(), 0);
        assertEquals("base tax", 100, taxInfo.getBaseTax(), 0);
        assertEquals("excess", .01, taxInfo.getExcess(), 0);
    }

    @Test
    public void taxBracketReturnsCorrectLargestTaxInfo() throws Exception {
        TaxInfo taxInfo = taxBracketManager.getTaxInfo(1001.01);

        assertEquals("tax rate", .07, taxInfo.getTaxRate(), 0);
        assertEquals("base tax", 500, taxInfo.getBaseTax(), 0);
        assertEquals("excess", .01, taxInfo.getExcess(), 0);
    }

    @Test
    public void taxBracketReturnsCorrectTaxInfoForBracketWithoutMaximum() throws Exception {
        TaxInfo taxInfo = taxBracketManager.getTaxInfo(3000);

        assertEquals("tax rate", .10, taxInfo.getTaxRate(), 0);
        assertEquals("base tax", 1000, taxInfo.getBaseTax(), 0);
        assertEquals("excess", 1000, taxInfo.getExcess(), 0);
    }

}