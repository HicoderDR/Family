package com.database.demo.Repository;

import com.database.demo.Entity.Goodtype;
import com.database.demo.Entity.Ord;
import com.database.demo.Entity.Stuff;
import com.database.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrdRepository extends JpaRepository<Ord,Integer> {

    @Query (value = "select * from ord where vip_id=?1", nativeQuery = true)
    public List<Ord> selectbyvipid(String id);

}