package com.database.demo.Repository;

import com.database.demo.Entity.Goodtype;
import com.database.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GoodtypeRepository extends JpaRepository<Goodtype,Integer> {

    @Query(value = "select * from goodtype", nativeQuery = true)
    public List<Goodtype> selectall();

    @Query(value = "select * from goodtype where category=?1", nativeQuery = true)
    public List<Goodtype> selectcategory(String category);

    @Query(value = "select DISTINCT category from goodtype", nativeQuery = true)
    public List<String> allcategory();
}