package com.database.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public String returnhome(ModelMap map){
        return "HomePage";
    }

    @RequestMapping(value = "/shop" ,method = RequestMethod.GET)
    public String returnshop(ModelMap map){
        return "ShoppingList";
    }
}
