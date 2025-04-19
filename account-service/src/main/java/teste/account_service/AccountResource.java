package teste.account_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountResource {
    
    @GetMapping("/")
    public String hello() {
        return "hello!";
    }

}
