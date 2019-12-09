package com.database.demo.Service;

import com.database.demo.Entity.Goodtype;
import com.database.demo.Entity.Ord;
import com.database.demo.Repository.GoodtypeRepository;
import com.database.demo.Repository.OrdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OrdService")
public class OrdService {
    @Autowired
    OrdRepository ordRepository;

    public List<Ord> selectall(){
        return ordRepository.findAll();
    }
    public List<Ord> selectbyvipid(String id){
        return ordRepository.selectbyvipid(id);
    }
    public void deleteall(){
        ordRepository.deleteAll();
    }
    public void addnew(Ord ord){
        ordRepository.save(ord);
    }
}
