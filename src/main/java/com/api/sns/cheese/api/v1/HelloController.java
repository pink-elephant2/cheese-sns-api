package com.api.sns.cheese.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello, World!";
    }
}
