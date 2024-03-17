package us.dev.shipandcargo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "Home";
    }

    @GetMapping("/register")
    public String reg() {
        return "register";
    }

}
