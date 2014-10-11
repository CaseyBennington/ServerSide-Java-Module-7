package com.corejsf;

import data.DBUtil;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.sql.*;
import javax.annotation.Resource;

@ManagedBean(name = "user")
@SessionScoped
public class MainBidBean implements Serializable {

    @Resource(name = "jdbc/vacation")
    private String ID;
    private String destination;
    private Date date;
    private double bidAmount;

    public MainBidBean() {
    }

    public MainBidBean(String ID, String destination, Date date, double bidAmount) {
        this.ID = ID;
        this.destination = destination;
        this.date = date;
        this.bidAmount = bidAmount;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String addToDB() {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Connection c = DBUtil.connect();
        String query =
                "INSERT INTO bid ( userID, destination, deptDate, bidAmt) VALUES(\"" + ID
                + "\",\"" + destination + "\",\"" + sqlDate + "\"," + bidAmount + ")";
        System.out.println(query);
        try {
            Statement s = c.createStatement();
            s.executeUpdate(query);
            System.out.println("Added");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }
}