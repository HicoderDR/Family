package com.database.demo.Service;

import com.database.demo.Entity.Good;
import com.database.demo.Entity.Goodtype;
import com.database.demo.Repository.GoodRepository;
import com.database.demo.Repository.GoodtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("GoodService")
public class GoodService {

    @Autowired
    GoodRepository goodRepository;
    @Autowired
    GoodtypeRepository goodtypeRepository;
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

    public void sell(String goodname,int num){
        List<Good> list=goodRepository.getgoods(goodname);
        for(int i=0;i<num;i++){
            goodRepository.setstate(list.get(i).getGoodID(),"售出");
        }
        goodtypeRepository.settodaysale(goodname,num);
    }
}
