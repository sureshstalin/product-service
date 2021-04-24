package com.itgarden.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/test")
public class TestController {

    @GetMapping
    public String defaultEndPoint() {
        return "Product Service is up and running";
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){

        return new ResponseEntity<>("Product Service is up and running...", HttpStatus.OK);
    }
}
