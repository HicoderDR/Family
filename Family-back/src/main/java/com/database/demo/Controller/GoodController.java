package com.database.demo.Controller;


import com.database.demo.Common.Response;
import com.database.demo.Entity.Good;
import com.database.demo.Service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.database.demo.Common.ResultGenerator.genFailResult;
import static com.database.demo.Common.ResultGenerator.genSuccessResult;

@RestController
@CrossOrigin
@RequestMapping("/good")
public class GoodController {

    @Autowired
    GoodService goodService;

    @PostMapping("/add")
    public Response add(@RequestParam String goodid,@RequestParam String goodname,@RequestParam String makedate){
        try{
            Good a=new Good();
            a.setGoodID(goodid);
            a.setGoodname(goodname);
            a.setMakedate(makedate);
            a.setState("在售");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date mkdate=formatter.parse(makedate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(mkdate);
            cal.add(Calendar.DATE, goodService.getguarantee(goodname));
            Date eddate=cal.getTime();
            String end=formatter.format(eddate);
            a.setEnddate(end);
            return genSuccessResult(goodService.addone(a));
        }catch (Exception e){
            return genFailResult(e.toString());
        }
    }

    @PostMapping("/sell")
    public Response sell(@RequestParam(value = "goodlist[]") String[] goodlist,@RequestParam(value = "numlist[]") int[] numlist){
        try{
            for(int i=0;i<goodlist.length;i++){
                String goodname=goodlist[i];
                int num=numlist[i];
                goodService.sell(goodname,num);
            }
            return genSuccessResult("修改成功");
        }catch (Exception e){
            return genFailResult(e.toString());
        }
    }
    @PostMapping("/allguarantee")
    public Response allguarantee(){
        try{
            goodService.allguarantee();
            return genSuccessResult("更新成功");
        }catch (Exception e){
            return genFailResult(e.toString());
        }
    }

    @PostMapping("/deleteall")
    public Response deleteall(){
        try{
            goodService.deleteall();
            return genSuccessResult("清空成功");
        }catch (Exception e){
            return genFailResult(e.toString());
        }
    }
}
