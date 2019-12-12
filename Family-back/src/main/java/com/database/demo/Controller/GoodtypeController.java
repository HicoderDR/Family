package com.database.demo.Controller;

import com.database.demo.Common.Response;
import com.database.demo.Entity.Goodtype;
import com.database.demo.Entity.User;
import com.database.demo.Service.GoodtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.database.demo.Common.ResultGenerator.genFailResult;
import static com.database.demo.Common.ResultGenerator.genSuccessResult;
@RestController
@CrossOrigin
@RequestMapping("/goodtype")
public class GoodtypeController {

    @Autowired
    GoodtypeService goodtypeService;


    @GetMapping("/all")
    public Response all(){
        try {
            List<Goodtype> list=goodtypeService.selectall();
            return genSuccessResult(list);
        }
        catch (Exception e){
           return genFailResult("get message fail");
        }
    }

    @GetMapping("/category")
    public Response category(@RequestParam String category){
        try {
            List<Goodtype> list=goodtypeService.selectbycate(category);
            return genSuccessResult(list);
        }
        catch (Exception e){
            return genFailResult("get message fail");
        }
    }

    @GetMapping("/categorylist")
    public Response categorylist(){
        try {
            List<String> list=goodtypeService.allcate();
            return genSuccessResult(list);
        }
        catch (Exception e){
            return genFailResult("get message fail");
        }
    }
    @PostMapping("/updatesale")
    public Response updatesale(){
        try{
            goodtypeService.updatesale();
            return genSuccessResult("更新成功");
        }catch (Exception e){
            return genFailResult("更新失败");
        }
    }
    @PostMapping("/add")
    public Response add(@RequestParam String type,@RequestParam String category,@RequestParam double price,@RequestParam String url,@RequestParam String desc){
        try{
            Goodtype x=new Goodtype();
            x.setType(type);
            x.setCategory(category);
            x.setPrice(price);
            x.setUri(url);
            x.setDescription(desc);
            goodtypeService.addnew(x);
            return genSuccessResult();
        }catch (Exception e){
            return genFailResult("添加失败");
        }
    }

    @PostMapping("/deleteall")
    public Response deleteall(){
        try{
            goodtypeService.deleteall();
            return genSuccessResult("清空成功");
        }catch(Exception e){
            return genFailResult("清空失败");
        }
    }

    @PostMapping("/setguarantee")
    public Response setguarantee(String goodname,int guarantee){
        try{
            goodtypeService.setguarantee(goodname,guarantee);
            return genSuccessResult("修改成功");
        }catch(Exception e){
            return genFailResult("修改失败");
        }
    }
}
