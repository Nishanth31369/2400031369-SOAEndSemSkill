package com.klu;

import org.springframework.web.bind.annotation.*;

@RestController
public class MS1Controller {

    private final MS1Service service;

    public MS1Controller(MS1Service service) {
        this.service = service;
    }

    @GetMapping("/add")
    public int add(@RequestParam int a,
                   @RequestParam int b) {
        return service.add(a, b);
    }
}