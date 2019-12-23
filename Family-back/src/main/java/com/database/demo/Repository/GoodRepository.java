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

    @Transactional
    @Modifying
    @Query(value = "update good set state='在售' where state='售出'", nativeQuery = true)
    public void allback();

    @Query(value = "select guarantee from goodtype where typex=?1", nativeQuery = true)
    public int getguarantee(String goodname);

    @Query(value = "select * from good where good_name=?1 and state='在售' order by makedate", nativeQuery = true)
    public List<Good> getgoods(String goodname);

    @Transactional
    @Modifying
    @Query(value = "update good set state=?2 where good_id=?1", nativeQuery = true)
    public void setstate(String goodid,String state);
}