package kc.domain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testowy")
public class Testowy {



    @GetMapping
    public String wyswietl(){
        return "testpwy";
    }

}
