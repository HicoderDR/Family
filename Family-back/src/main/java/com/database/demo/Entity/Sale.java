package com.database.demo.Entity;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    int saleID;

    @Column
    String saledate;

    @Column(columnDefinition = "char(200) character set utf8")
    String goodname;

    @Column
    int num;

    public Sale(){}
    public Sale(String goodname,String saledate,int num){
        this.goodname=goodname;
        this.saledate=saledate;
        this.num=num;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setSaledate(String saledate) {
        this.saledate = saledate;
    }

    public String getSaledate() {
        return saledate;
    }

    public int getNum() {
        return num;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }
}
