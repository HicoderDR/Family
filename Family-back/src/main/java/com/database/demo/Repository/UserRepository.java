package com.database.demo.Repository;

import com.database.demo.Entity.Stuff;
import com.database.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "select * from userx where username=?1", nativeQuery = true)
    public User selectuserbyname(String username);

    @Query(value = "select * from userx where user_id=?1", nativeQuery = true)
    public List<User> selectuserbyid(String userid);

    @Transactional
    @Modifying
    @Query(value = "update userx set balance=balance-?2 where user_id=?1", nativeQuery = true)
    public void pay(String userid,double money);

    @Transactional
    @Modifying
    @Query(value = "update userx set vip_score=vip_score-?2 where user_id=?1", nativeQuery = true)
    public void payscore(String userid,int score);

}