package com.database.demo.Entity;

import javax.persistence.*;

@Entity(name = "good")
public class Good {

    @Id
    @Column(name = "good_id")
    String goodID;

    @Column(name = "good_name",columnDefinition = "char(200) character set utf8")
    String goodname;

    @Column
    String makedate;

    @Column
    String enddate;

    @Column(columnDefinition = "char(200) character set utf8")
    String state;
    public  Good(){}
    public Good(String goodID,String goodname,String makedate,String enddate,String state){
        this.goodID=goodID;
        this.goodname=goodname;
        this.makedate=makedate;
        this.state=state;
        this.enddate=enddate;
    }

    public String getEnddate() {
        return enddate;
    }

    public String getGoodID() {
        return goodID;
    }

    public String getGoodname() {
        return goodname;
    }

    public String getMakedate() {
        return makedate;
    }

    public String getState() {
        return state;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public void setGoodID(String goodID) {
        this.goodID = goodID;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public void setMakedate(String makedate) {
        this.makedate = makedate;
    }

    public void setState(String state) {
        this.state = state;
    }
}
