package com.database.demo.Repository;

import com.database.demo.Entity.Stuff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface StuffRepository extends JpaRepository<Stuff,Integer> {

    @Query (value = "select * from stuff", nativeQuery = true)
    public List<Stuff> allstuff();

    @Query (value = "select * from stuff where stuff_id=?1", nativeQuery = true)
    public List<Stuff> selectbyid(String id);

    @Transactional
    @Modifying
    @Query (value = "update stuff set stuff_name=?2,sex=?3,ident=?4,belong=?5,job=?6,password=?7 where stuff_id=?1", nativeQuery = true)
    public void updatestuff(String stuff_id,String name,String sex,String ident,String belong,String job,String password);
}

