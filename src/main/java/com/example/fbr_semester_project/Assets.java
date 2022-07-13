package com.example.fbr_semester_project;

public class Assets {
    private int id;
    private String name;
    private double worth;
    private String date;
    private String type;
    private double tax;

    public Assets(int id, double worth, double tax) {
        this.id = id;
        this.worth = worth;
        this.tax = tax;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
