package com.database.demo.Controller;

import com.database.demo.Common.Response;
import com.database.demo.Entity.Stuff;
import com.database.demo.Entity.User;
import com.database.demo.Service.StuffService;
import com.database.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.database.demo.Common.ResultGenerator.genFailResult;
import static com.database.demo.Common.ResultGenerator.genSuccessResult;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    StuffService stuffService;

    @PostMapping("/login")
    public Response login(@RequestParam String username, @RequestParam String password, HttpSession session){
        try {
            User user=userService.selectbyid(username);
            Stuff stuff=stuffService.selectbyid(username);
            if(user==null&&stuff==null) return genFailResult("用户名不存在");
            else if(user!=null&&user.getPassword().equals(password)){
                return genSuccessResult(user);
            }else if(stuff!=null&&stuff.getPassword().equals(password)){
                return genSuccessResult(stuff);
            }
            else return genFailResult("密码错误");
        }
        catch (Exception e){
            return genFailResult("服务器未响应");
        }
    }

    @GetMapping("/getone")
    public Response getone(@RequestParam String userID){
        try{
            User x=userService.selectbyid(userID);
            if(x!=null) return genSuccessResult(x);
            else return genFailResult("获取失败");
        }catch (Exception e){
            return genFailResult("服务器未响应");
        }
    }

    @PostMapping("/deleteall")
    public Response deleteall(){
        try{
            userService.deleteall();
            return genSuccessResult("清空成功");
        }catch(Exception e){
            return genFailResult("删除失败");
        }
    }
}
