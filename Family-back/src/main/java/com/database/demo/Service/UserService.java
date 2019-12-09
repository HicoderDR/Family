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
}
