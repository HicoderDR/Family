package com.database.demo.Entity;

import javax.persistence.*;

@Entity(name = "goodtype")
public class Goodtype {

    @Id
    @Column(name = "typex",columnDefinition = "char(200) character set utf8")
    String type;

    @Column(columnDefinition = "char(200) character set utf8")
    String category;

    @Column(columnDefinition = "char(200) character set utf8")
    String description;

    @Column
    double price;

    @Column
    String buysend;

    @Column
    int total;

    @Column
    int guarantee;

    @Column
    int todaysale;

    @Column
    String uri;
    public Goodtype(){}


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public int getGuarantee() {
        return guarantee;
    }

    public int getTodaysale() {
        return todaysale;
    }

    public int getTotal() {
        return total;
    }

    public String getBuysend() {
        return buysend;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public void setBuysend(String buysend) {
        this.buysend = buysend;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTodaysale(int todaysale) {
        this.todaysale = todaysale;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
