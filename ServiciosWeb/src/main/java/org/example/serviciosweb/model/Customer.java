package org.example.serviciosweb.model;

import java.time.LocalDate;
import java.util.Date;

public class Customer {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private Date date_of_birth;
    private String country;
    private String city;
    private String address;
    private Double annual_income;
    private Double credit_score;
    private boolean has_unpaid_debt;
    private Double unpaid_debt_amount;
    private String status;
    private LocalDate created_at;


    public Customer(int id, String first_name, String last_name, String email, String phone, Date date_of_birth, String country, String city, String address, Double annual_income, Double credit_score, boolean has_unpaid_debt, Double unpaid_debt_amount, String status, LocalDate created_at) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.city = city;
        this.address = address;
        this.annual_income = annual_income;
        this.credit_score = credit_score;
        this.has_unpaid_debt = has_unpaid_debt;
        this.unpaid_debt_amount = unpaid_debt_amount;
        this.status = status;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Double getAnnual_income() {
        return annual_income;
    }

    public Double getCredit_score() {
        return credit_score;
    }

    public boolean isHas_unpaid_debt() {
        return has_unpaid_debt;
    }

    public Double getUnpaid_debt_amount() {
        return unpaid_debt_amount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }
}