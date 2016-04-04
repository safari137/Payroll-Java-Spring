package com.dillselectric.payroll.model;

public class TaxInfo {
    private double minimumGross;
    private double maximumGross;
    private double baseTax;
    private double taxRate;
    private double excess;

    public TaxInfo(double minimumGross, double maximumGross, double baseTax, double taxRate) {
        this.minimumGross = minimumGross;
        this.maximumGross = maximumGross;
        this.baseTax = baseTax;
        this.taxRate = taxRate;
    }

    public double getMinimumGross() {
        return minimumGross;
    }

    public void setMinimumGross(double minimumGross) {
        this.minimumGross = minimumGross;
    }

    public double getMaximumGross() {
        return maximumGross;
    }

    public void setMaximumGross(double maximumGross) {
        this.maximumGross = maximumGross;
    }

    public double getBaseTax() {
        return baseTax;
    }

    public void setBaseTax(double baseTax) {
        this.baseTax = baseTax;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getExcess() {
        return excess;
    }

    public void setExcess(double excess) {
        this.excess = excess;
    }
}
