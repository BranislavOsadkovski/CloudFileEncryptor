package com.example.controlers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class Shop {

    @RequestMapping(value = "/homepage")
    public String homePage(){
        return "homepage";
    }


    @Scheduled(cron = "0/10 * * * * ?")
    public void chronometar(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String date = sdf.format(now);
        System.out.println("Shop/Chrono fire : " + date);
    }
}
