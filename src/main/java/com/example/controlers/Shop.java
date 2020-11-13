package com.example.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Shop {

    @RequestMapping(value = "/homepage")
    public String homePage(){
        return "homepage";
    }

}
