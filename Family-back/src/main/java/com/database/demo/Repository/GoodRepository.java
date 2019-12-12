package com.database.demo.Repository;

import com.database.demo.Entity.Good;
import com.database.demo.Entity.Goodtype;
import com.database.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<Good,Integer> {

    @Transactional
    @Modifying
    @Query(value = "update good set state='过期' where enddate<?1", nativeQuery = true)
    public void allguarantee(String now);


    @Query(value = "select guarantee from goodtype where typex=?1", nativeQuery = true)
    public int getguarantee(String goodname);
}