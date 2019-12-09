package com.database.demo.Entity;

import javax.persistence.*;

@Entity (name = "stuff")
public class Stuff {
    @Id
    @Column (name = "stuff_id")
    String stuffid;

    @Column
    String password;

    @Column (name = "stuff_name",columnDefinition = "char(200) character set utf8")
    String name;

    @Column (name = "sex",columnDefinition = "char(200) character set utf8")
    String sex;

    @Column (name = "ident")
    String ident;

    @Column (name = "belong",columnDefinition = "char(200) character set utf8")
    String belong;

    @Column (name="job",columnDefinition = "char(200) character set utf8")
    String job;

    public  Stuff(){}
    public  Stuff(String stuffid,String password,String name,String sex,String ident,String belong,String job){
        this.stuffid=stuffid;
        this.password=password;
        this.belong=belong;
        this.ident=ident;
        this.job=job;
        this.name=name;
        this.sex=sex;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getStuffid() {
        return stuffid;
    }

    public String getBelong() {
        return belong;
    }

    public String getIdent() {
        return ident;
    }

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setStuffid(String stuffid) {
        this.stuffid = stuffid;
    }

}
