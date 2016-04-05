package com.dillselectric.payroll.service.brackets;

import com.dillselectric.payroll.model.TaxInfo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TaxBracketManager {
    private List<TaxInfo> taxInfoList = new ArrayList<>();

    public TaxBracketManager(List<TaxInfo> taxInfoList) {
        this.taxInfoList = taxInfoList;
    }

    public TaxInfo getTaxInfo(double adjustedGross) {
        for (TaxInfo taxInfo : taxInfoList) {
            boolean meetsMinimumRequirement = (adjustedGross > taxInfo.getMinimumGross());
            boolean meetsMaximumRequirement = (adjustedGross <= taxInfo.getMaximumGross() || taxInfo.getMaximumGross() == 0);

            if (meetsMinimumRequirement && meetsMaximumRequirement) {
                taxInfo.setExcess(this.calculateExcess(adjustedGross, taxInfo.getMinimumGross()));
                return taxInfo;
            }
        }
        return null;
    }

    private double calculateExcess(double gross, double baseAmount) {
        double excess = new BigDecimal(gross - baseAmount).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

        return excess;
    }
}
