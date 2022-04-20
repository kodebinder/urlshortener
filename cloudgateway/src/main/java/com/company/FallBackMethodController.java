package com.company;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:8765"})
public class FallBackMethodController {

    @GetMapping("/urlServiceFallBack")
    public String urlServiceFallBackMethod() {
        return "Url Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackMethod() {
        return "User Service is taking longer than Expected." +
                " Please try again later";
    }

}