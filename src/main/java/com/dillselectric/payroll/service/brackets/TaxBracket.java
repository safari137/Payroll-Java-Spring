package com.dillselectric.payroll.service.brackets;

import com.dillselectric.payroll.model.TaxInfo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TaxBracket {
    private List<TaxInfo> taxInfoList = new ArrayList<>();

    public TaxBracket(List<TaxInfo> taxInfoList) {
        this.taxInfoList = taxInfoList;
    }

    public TaxInfo getTaxInfo(double adjustedGross) {
        for (TaxInfo taxInfo : taxInfoList) {
            if (adjustedGross > taxInfo.getMinimumGross() && adjustedGross <= taxInfo.getMaximumGross()) {
                taxInfo.setExcess(this.calculateExcess(adjustedGross, taxInfo.getMinimumGross()));
                return taxInfo;
            }
        }

        return null;
    }

    private double calculateExcess(double gross, double baseAmount) {
        if (baseAmount == 0)
            return 0;

        double excess = new BigDecimal(gross - baseAmount).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

        return excess;
    }
}
