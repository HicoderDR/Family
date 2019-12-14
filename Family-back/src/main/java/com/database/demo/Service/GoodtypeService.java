package com.database.demo.Service;

import com.database.demo.Entity.Goodtype;
import com.database.demo.Repository.GoodRepository;
import com.database.demo.Repository.GoodtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service("GoodtypeService")
public class GoodtypeService {
    @Autowired
    GoodRepository goodRepository;
    @Autowired
    GoodtypeRepository goodtypeRepository;
    public void setguarantee(String goodname,int guarantee){
        goodtypeRepository.updateguarantee(goodname,guarantee);
    }

    public void setbuysend(String goodname,String buysend){
        goodtypeRepository.setbuysend(goodname,buysend);
    }


    public void updatesale(){
        goodtypeRepository.updatesale();
    }

    public List<Goodtype> selectall(){
        return goodtypeRepository.selectall();
    }

    public List<Goodtype> selectbycate(String cate){
        return goodtypeRepository.selectcategory(cate);
    }

    public List<String> allcate(){
        return goodtypeRepository.allcategory();
    }

    public void addnew(Goodtype x){
        goodtypeRepository.save(x);
    }

    public void deleteone(Goodtype x){
        goodtypeRepository.delete(x);
    }
    public void deleteall(){
        goodtypeRepository.deleteAll();
    }

    public void timer(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Date now=new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String nowstr=formatter.format(now);
                goodRepository.allguarantee(nowstr);
                goodtypeRepository.updatesale();
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 1000, 24*60*60*1000);
    }
}
