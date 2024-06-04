package com.csc3402.lab.users.model;

import jakarta.persistence.*;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userid;

    @Column(name = "first_name")
    private String fname;

    @Column(name = "last_name")
    private String lname;

    private String email;

    private String password;

    private Integer phone;

    @Column(name = "total_amount")
    private Integer totamount;

    // Constructors
    public Users() {
    }

    public Users(String fname, String lname, String email, String password, Integer phone, Integer totamount) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.totamount = totamount;
    }

    // Getter and setters
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getTotamount() {
        return totamount;
    }

    public void setTotamount(Integer totamount) {
        this.totamount = totamount;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userid=" + userid +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", totamount=" + totamount +
                '}';
    }
}
