package com.hch.controller;

import com.hch.api.response.CommonResponse;
import com.hch.config.CustomProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
// @RestController
public class HelloController {
    @Autowired
    private CustomProperties customProperties;

    @Value("${custom.author.name}")
    private String name;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiIgnore
    public String forwardSwagger() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping(value = "/hello", produces = "application/JSON")
    @ResponseBody  // 该注解表示方法所返回的对象即为返回体，而不是视图名
    public CommonResponse<String> hello(@RequestParam(value = "name", required = false) String name) {
        name = Optional.ofNullable(name).orElse(this.name);
        log.info("get hello request, name: " + name);
        CommonResponse<String> response = new CommonResponse<>();
        response.setData("hello, " + name);
        return response;
    }

    @PostMapping(value = "/error", produces = "application/JSON")
    public CommonResponse<String> error(@RequestBody Map<String,Object> request) {
        if (!request.containsKey("name")) {
            throw new IllegalArgumentException("missing argument 'name'");
        }
        throw new RuntimeException("argument 'name' found, but this is a problematic api :(");
    }

    @RequestMapping(value = "/author", method = RequestMethod.GET, produces = "application/JSON")
    @ResponseBody
    public CustomProperties.Person getPerson(HttpServletRequest request) {
        // Enumeration<String> headerNames = request.getHeaderNames();
        for (Enumeration<String> headerNames = request.getHeaderNames(); headerNames.hasMoreElements(); ) {
            String headerName = headerNames.nextElement();
            System.out.printf("%-20s:%20s\n", headerName, request.getHeader(headerName));
        }
        return customProperties.getAuthor();
    }

    // private void privateMethod() {
    //     publicMethod();
    // }
    //
    // public void publicMethod() {
    //     privateMethod();
    // }
}
