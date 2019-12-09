package com.database.demo.Service;

import com.database.demo.Entity.Goodtype;
import com.database.demo.Repository.GoodtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GoodtypeService")
public class GoodtypeService {

    @Autowired
    GoodtypeRepository goodtypeRepository;

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
}
