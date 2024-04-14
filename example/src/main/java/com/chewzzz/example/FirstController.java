package com.chewzzz.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FirstController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "user-name", required = false) String userName) {
        return userName==null ? "First time?" : "Hello, we are glad to see you, " + userName;
    }

    @GetMapping("/hello/{user-name}")
    public String pathVar(@PathVariable("user-name") String userName) {
        return "Hello, user " + userName;
    }

    @PostMapping("/post")
    public String post(@RequestBody String message) {
        return "Request accepted. Message accepted is: " + message;
    }

    @PostMapping("/post-order")
    public String postOrder(@RequestBody Order order) {
        return "Request accepted. Order accepted is: " + order.toString();
    }

    @PostMapping("/post-order-record")
    public String postRecord(@RequestBody OrderRecord orderRecord) {
        return "Request accepted. Order accepted is: " + orderRecord.toString();
    }
}
