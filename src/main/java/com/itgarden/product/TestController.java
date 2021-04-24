package com.itgarden.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping
    public String defaultEndPoint() {
        return "Product Service is up and running";
    }
}
