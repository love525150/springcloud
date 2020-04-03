package org.allen.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.allen.user.bus.event.UserUpdateEvent;
import org.allen.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zhou Zhengwen
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationContext applicationContext;
    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping("name")
    public String name(Integer id, HttpServletRequest request) {
        return userService.getName(id);
    }

    @HystrixCommand(fallbackMethod = "fail1", commandProperties = {
        @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
        @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"),
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "50000"),
        @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10"),
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
    })
    @GetMapping("test1")
    public String test1(int id) throws InterruptedException {
        if (id % 2 == 0) {
            return "ok";
        } else {
            Thread.sleep(1000);
            return "ok";
        }
    }

    private String fail1(int id, Throwable e) {
        return "fail1";
    }

    @RequestMapping("update")
    public String update(String name) {
        String id = applicationContext.getApplicationName();
        UserUpdateEvent userUpdateEvent = new UserUpdateEvent(name, id, "**");
        applicationContext.publishEvent(userUpdateEvent);
        return "ok";
    }
}
