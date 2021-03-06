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

    @Transactional
    @Modifying
    @Query(value ="update goodtype set total=IFNULL((select cnt from(SELECT count(*) as cnt,good_name from good where state='在售' GROUP BY good_name) as a where typex=a.good_name),0)" , nativeQuery = true)
    public void updatesale();

    @Transactional
    @Modifying
    @Query(value = "update goodtype set guarantee=?2 where typex=?1", nativeQuery = true)
    public void updateguarantee(String goodname,int guarantee);

    @Transactional
    @Modifying
    @Query(value = "update goodtype set buysend=?2 where typex=?1", nativeQuery = true)
    public void setbuysend(String goodname,String guarantee);

    @Transactional
    @Modifying
    @Query(value = "update goodtype set todaysale=todaysale+?2 where typex=?1", nativeQuery = true)
    public void settodaysale(String goodname,int todaysale);
}
