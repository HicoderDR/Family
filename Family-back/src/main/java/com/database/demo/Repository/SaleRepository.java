package com.database.demo.Repository;

import com.database.demo.Entity.Sale;
import com.database.demo.Entity.Stuff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SaleRepository  extends JpaRepository<Sale,Integer> {

    @Query(value = "select * from sale", nativeQuery = true)
    public List<Sale> selectall();

    @Query(value = "select * from sale where goodname=?1", nativeQuery = true)
    public List<Sale> selectbygoodname(String goodname);
}
