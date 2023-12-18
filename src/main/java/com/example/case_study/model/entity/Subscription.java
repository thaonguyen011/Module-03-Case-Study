package com.example.case_study.model.entity;

import java.sql.Timestamp;

public class Subscription {
    private int id;
    private String name;
    private int userId;
    private Timestamp startDate;
    private Timestamp endDate;
    private boolean status;

    public Subscription() {
    }

    public Subscription(int id, String name, int userId, Timestamp startDate, Timestamp endDate, boolean status) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
