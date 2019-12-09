package com.database.demo.Controller;


import com.database.demo.Common.Response;
import com.database.demo.Entity.Ord;
import com.database.demo.Entity.User;
import com.database.demo.Service.OrdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.database.demo.Common.ResultGenerator.genFailResult;
import static com.database.demo.Common.ResultGenerator.genSuccessResult;

@RestController
@CrossOrigin
@RequestMapping("/ord")
public class OrdController {

    @Autowired
    OrdService ordService;

    @GetMapping("/all")
    public Response all(){
        try{
            List<Ord> list=ordService.selectall();
            return genSuccessResult(list);
        }catch (Exception e){
            return genFailResult("获取失败");
        }
    }

    @GetMapping("/selectbyvipid")
    public Response all(@RequestParam String vipid){
        try{
            List<Ord> list=ordService.selectbyvipid(vipid);
            return genSuccessResult(list);
        }catch (Exception e){
            return genFailResult("获取失败");
        }
    }

    @PostMapping("/add")
    public Response add(@RequestParam String vipid,@RequestParam double money,@RequestParam String stuffid){
        try{
            String uuid= UUID.randomUUID().toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date current=new Date();
            String date=formatter.format(current);
            Ord ord=new Ord(uuid,date,vipid,money,stuffid);
            ordService.addnew(ord);
            return genSuccessResult(ord);
        }catch (Exception e){
            return genFailResult("添加失败");
        }
    }

    @PostMapping("/deleteall")
    public Response deleteall(){
        try{
            ordService.deleteall();
            return genSuccessResult("清空成功");
        }catch(Exception e){
            return genFailResult("删除失败");
        }
    }
}
