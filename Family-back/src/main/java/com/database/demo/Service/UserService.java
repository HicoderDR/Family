package com.database.demo.Service;


import com.database.demo.Entity.User;
import com.database.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void adduser(User x){
        userRepository.save(x);
    }

    public User select(String username){
        User x=userRepository.selectuserbyname(username);
        return x;
    }
    public void deletebyid(int id){
        userRepository.deleteById(id);
    }
    public void deleteuser(User x){
        userRepository.delete(x);
    }
    public User selectbyid(String id){
        List<User> list= userRepository.selectuserbyid(id);
        if(list.size()==0) return null;
        return list.get(0);
    }
    public void deleteall(){
        userRepository.deleteAll();
    }
    public void addscore(String userid,int score){
        userRepository.payscore(userid,-score);
    }

    public Boolean pay(String userid,double money){
        List<User> list=userRepository.selectuserbyid(userid);
        if(list.size()>0){
            double balance=list.get(0).getBalance();
            if(balance>=money) {
                userRepository.pay(userid, money);
                return true;
            }
        }
        return false;
    }
    public Boolean paybyscore(String userid,double money){
        List<User> list=userRepository.selectuserbyid(userid);
        if(list.size()>0){
            double balance=list.get(0).getBalance();
            int score=list.get(0).getVipscore();
            if((1.0*score)>=money*100)  {
                int x= (int) money*100;
                userRepository.payscore(userid,x);
                return true;
            }else {
                if (balance >= money-score*1.0/100) {
                    userRepository.payscore(userid,score);
                    userRepository.pay(userid, money);
                    return true;
                }else return false;
            }
        }
        return false;
    }
}
