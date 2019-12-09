package com.database.demo.Entity;

import javax.persistence.*;

@Entity(name = "store")
public class Store {

    @Id
    @Column (name = "store_id")
    String storeid;

    @Column (name = "store_name",columnDefinition = "char(200) character set utf8")
    String name;

    @Column (name = "address",columnDefinition = "char(200) character set utf8")
    String address;

    @Column (name = "charger",columnDefinition = "char(200) character set utf8")
    String charger;

    @Column (name = "phone")
    String phone;

    @Column (name = "provider",columnDefinition = "char(200) character set utf8")
    String provider;
    public Store(){}
    public Store(String storeid,String name,String address,String charger,String phone,String provider){
        this.address=address;
        this.storeid=storeid;
        this.charger=charger;
        this.name=name;
        this.phone=phone;
        this.provider=provider;
    }
}
