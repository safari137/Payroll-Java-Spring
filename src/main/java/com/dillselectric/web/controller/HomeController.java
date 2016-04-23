package com.dillselectric.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
    @RequestMapping("/")
    public String Index(ModelMap modelMap) {
        modelMap.put("page", "home");

        return "home/home";
    }
}
