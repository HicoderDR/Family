package com.database.demo.Entity;

import javax.persistence.*;

@Entity(name = "ord")
public class Ord {

    @Id
    @Column(name = "ord_id")
    String ordID;

    @Column
    String saledate;

    @Column(name = "vip_id")
    String vipid;

    @Column
    double money;

    @Column (name = "stuff_id")
    String stuffID;
    public Ord(){}
    public Ord(String ordID,String saledate,String vipid,double money,String stuffID){
        this.money=money;
        this.ordID=ordID;
        this.saledate=saledate;
        this.stuffID=stuffID;
        this.vipid=vipid;
    }

    public void setVipid(String vipid) {
        this.vipid = vipid;
    }

    public String getVipid() {
        return vipid;
    }

    public double getMoney() {
        return money;
    }

    public String getSaledate() {
        return saledate;
    }

    public String getOrdID() {
        return ordID;
    }

    public String getStuffID() {
        return stuffID;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setOrdID(String ordID) {
        this.ordID = ordID;
    }

    public void setSaledate(String saledate) {
        this.saledate = saledate;
    }

    public void setStuffID(String stuffID) {
        this.stuffID = stuffID;
    }
}
