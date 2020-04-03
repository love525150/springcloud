package org.allen.controller;

import brave.*;
import org.allen.feign.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author Zhou Zhengwen
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private Tracer tracer;
//    @Autowired
//    private SpanCustomizer span;
    @Autowired
    private Tracing tracing;

    @RequestMapping("create")
    public String create() {
        String userId = restTemplate.getForObject("http://user-service/user/name?id={id}", String.class, 0);
        System.out.println(userId);
        return "ok";
    }

    @RequestMapping("delete")
    public String delete() {
        String userId = userService.name(0);
        System.out.println(userId);
        return "ok";
    }

    @RequestMapping("update")
    public String update(Integer error) throws InterruptedException {
        Thread.sleep(50);
//        ScopedSpan span = tracer.startScopedSpan("innerSpan");
        Span span = tracer.nextSpan().name("innerSpan").start();
        span.annotate("hello span");
        span.tag("innerSpan.key", "100");
        span.tag("innerSpan.value", "1000");
        /*Thread.sleep(50);
        span.finish();
        Thread.sleep(50);*/
        Optional.ofNullable(error).ifPresent(e -> {
            throw new RuntimeException();
        });
        return "ok";
    }
}
