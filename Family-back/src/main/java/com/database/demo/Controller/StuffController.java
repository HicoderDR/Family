package com.database.demo.Controller;


import com.database.demo.Common.Response;
import com.database.demo.Entity.Stuff;
import com.database.demo.Entity.User;
import com.database.demo.Repository.StuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.database.demo.Common.ResultGenerator.genFailResult;
import static com.database.demo.Common.ResultGenerator.genSuccessResult;

@RestController
@CrossOrigin
@RequestMapping("/stuff")
public class StuffController {

    @Autowired
    StuffRepository stuffRepository;

    @GetMapping("/getone")
    public Response getone(@RequestParam String stuffid){
        try{
            List<Stuff> list=stuffRepository.selectbyid(stuffid);
            if(list.size()>0) return genSuccessResult(list.get(0));
            else return genFailResult("获取失败");
        }catch (Exception e){
            return genFailResult("服务器未响应");
        }
    }
}
