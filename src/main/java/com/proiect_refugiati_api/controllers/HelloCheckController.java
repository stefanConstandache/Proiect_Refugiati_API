package com.proiect_refugiati_api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloCheckController {

    @GetMapping(value = "/check/{myInt}")
    public @ResponseBody String getCheckData(@PathVariable Integer myInt) {
        return "Working " + myInt;
    }

}
