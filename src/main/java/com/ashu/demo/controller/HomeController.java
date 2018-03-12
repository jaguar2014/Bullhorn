package com.ashu.demo.controller;


import com.ashu.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    AppUserRepository appUserRepository;



    @GetMapping("/")
    public String showIndex(Model model){

        return "";

    }


    @GetMapping("/login")
    public String showLogin(){


        return "login";

    }
}
