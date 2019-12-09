package com.database.demo.Entity;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity (name = "userx")
public class User {

    @Id
    @Column(name = "user_id")
    String userid;

    @Column(name = "username",columnDefinition = "char(200) character set utf8")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "vip_type",columnDefinition = "char(200) character set utf8")
    int viptype;

    @Column(name = "vip_score")
    int vipscore;

    @Column(name ="vip_date")
    String vipdate;

    @Column(name = "balance")
    double balance;

    public User(String userid,String username,String password,int viptype,int vipscore,String vipdate,double balance){
        this.password=password;
        this.userid=userid;
        this.username=username;
        this.vipdate=vipdate;
        this.vipscore=vipscore;
        this.viptype=viptype;
        this.balance=balance;
    }
    public User(){}

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getVipscore() {
        return vipscore;
    }

    public int getViptype() {
        return viptype;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getVipdate() {
        return vipdate;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setVipdate(String vipdate) {
        this.vipdate = vipdate;
    }


    public void setVipscore(int vipscore) {
        this.vipscore = vipscore;
    }

    public void setViptype(int viptype) {
        this.viptype = viptype;
    }
}
