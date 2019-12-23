package com.database.demo.Controller;

import com.database.demo.Common.Response;
import com.database.demo.Entity.Ord;
import com.database.demo.Entity.Stuff;
import com.database.demo.Entity.User;
import com.database.demo.Repository.StuffRepository;
import com.database.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    GoodService goodService;
    @Autowired
    OrdService ordService;
    @Autowired
    GoodtypeService goodtypeService;
    @Autowired
    StuffRepository stuffRepository;
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
            List<Stuff> y=stuffRepository.selectbyid(userID);
            if(x!=null) return genSuccessResult(x);
            if(!y.isEmpty()) return genSuccessResult(y.get(0));
            else return genFailResult("获取失败");
        }catch (Exception e){
            return genFailResult("服务器未响应");
        }
    }
    //
    @PostMapping("/deleteall")
    public Response deleteall(){
        try{
            userService.deleteall();
            return genSuccessResult("清空成功");
        }catch(Exception e){
            return genFailResult("删除失败");
        }
    }

    @PostMapping("/pay")
    public Response pay(@RequestParam String userid,@RequestParam(value = "goodlist[]") String[] goodlist,@RequestParam(value = "numlist[]") int[] numlist,@RequestParam double money,@RequestParam String stuffid,@RequestParam int score){
        try{
            if(userService.pay(userid,money))  {
                StringBuilder goodstr= new StringBuilder();
                StringBuilder numstr= new StringBuilder();
                for(int i=0;i<goodlist.length;i++){
                    String goodname=goodlist[i];
                    int num=numlist[i];
                    if(i!=0) goodstr.append(',');
                    goodstr.append(goodlist[i]);
                    if(i!=0) numstr.append(',');
                    numstr.append(numlist[i]);
                    goodService.sell(goodname,num);
                }
                userService.addscore(userid,score);
                String uuid= UUID.randomUUID().toString();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date current=new Date();
                String date=formatter.format(current);

                Ord ord=new Ord(uuid,date,userid,money,stuffid,goodstr.toString(),numstr.toString());
                ordService.addnew(ord);

                goodtypeService.updatesale();
                return genSuccessResult("支付成功");
            }
            else                                return genFailResult("支付失败");
        }catch(Exception e){
            return genFailResult(e.toString());
        }
    }
    @PostMapping("/payscore")
    public Response payscore(@RequestParam String userid,@RequestParam(value = "goodlist[]") String[] goodlist,@RequestParam(value = "numlist[]") int[] numlist,@RequestParam double money,@RequestParam String stuffid,@RequestParam int score){
        try{
            if(userService.paybyscore(userid,money))  {
                StringBuilder goodstr= new StringBuilder();
                StringBuilder numstr= new StringBuilder();
                for(int i=0;i<goodlist.length;i++){
                    String goodname=goodlist[i];
                    int num=numlist[i];
                    if(i!=0) goodstr.append(',');
                    goodstr.append(goodlist[i]);
                    if(i!=0) numstr.append(',');
                    numstr.append(numlist[i]);
                    goodService.sell(goodname,num);
                }
                userService.addscore(userid,score);
                String uuid= UUID.randomUUID().toString();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date current=new Date();
                String date=formatter.format(current);

                Ord ord=new Ord(uuid,date,userid,money,stuffid,goodstr.toString(),numstr.toString());
                ordService.addnew(ord);

                goodtypeService.updatesale();
                return genSuccessResult("支付成功");
            }
            else                                return genFailResult("支付失败");
        }catch(Exception e){
            return genFailResult(e.toString());
        }
    }
}
