package com.database.demo.Service;

import com.database.demo.Entity.Good;
import com.database.demo.Entity.Goodtype;
import com.database.demo.Repository.GoodRepository;
import com.database.demo.Repository.GoodtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service("GoodService")
public class GoodService {

    @Autowired
    GoodRepository goodRepository;

    public int getguarantee(String goodname){
        return goodRepository.getguarantee(goodname);
    }
    public void allguarantee(){
        Date now=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String nowstr=formatter.format(now);
        goodRepository.allguarantee(nowstr);
    }
    public Good addone(Good x) {
        return goodRepository.save(x);
    }
    public void deleteall(){
        goodRepository.deleteAll();
    }
    public void deleteone(Good x){
        goodRepository.delete(x);
    }

}
