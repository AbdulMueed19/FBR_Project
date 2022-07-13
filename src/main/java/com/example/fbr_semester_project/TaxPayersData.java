package com.example.fbr_semester_project;

public class TaxPayersData {
    private int tax_id;
    private double calculated_tax;
    private double paid_tax;
    private String period_start;
    private String period_end;
    private double outstanding_balance;

    public TaxPayersData(int tax_id, double calculated_tax, double paid_tax, String period_start, String period_end, double outstanding_balance) {
        this.tax_id = tax_id;
        this.calculated_tax = calculated_tax;
        this.paid_tax = paid_tax;
        this.period_start = period_start;
        this.period_end = period_end;
        this.outstanding_balance = calculated_tax-paid_tax;
    }

    public int getTax_id() {
        return tax_id;
    }

    public void setTax_id(int tax_id) {
        this.tax_id = tax_id;
    }

    public double getCalculated_tax() {
        return calculated_tax;
    }

    public void setCalculated_tax(double calculated_tax) {
        this.calculated_tax = calculated_tax;
    }

    public double getPaid_tax() {
        return paid_tax;
    }

    public void setPaid_tax(double paid_tax) {
        this.paid_tax = paid_tax;
    }

    public String getPeriod_start() {
        return period_start;
    }

    public void setPeriod_start(String period_start) {
        this.period_start = period_start;
    }

    public String getPeriod_end() {
        return period_end;
    }

    public void setPeriod_end(String period_end) {
        this.period_end = period_end;
    }

    public double getOutstanding_balance() {
        return outstanding_balance;
    }

    public void setOutstanding_balance(double outstanding_balance) {
        this.outstanding_balance = outstanding_balance;
    }
}
