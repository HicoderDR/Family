package com.database.demo.Service;

import com.database.demo.Entity.Stuff;
import com.database.demo.Repository.StuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StuffService")
public class StuffService {
    @Autowired
    StuffRepository stuffRepository;

    public void addstuff(Stuff x){
        try{
            stuffRepository.save(x);
        }catch (Exception e){}
    }
    public Stuff selectbyid(String id){
        List<Stuff> list= stuffRepository.selectbyid(id);
        if(list.size()==0) return null;
        else return list.get(0);
    }
    public List<Stuff> allstuff(){
        return stuffRepository.allstuff();
    }
    public void deleteall(){
        stuffRepository.deleteAll();
    }
}
