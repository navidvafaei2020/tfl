package lu.cnfpcfullstackd.tfl_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello, To Food To Leave";
    }


}
