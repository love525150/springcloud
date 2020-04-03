package org.allen.user.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Zhou Zhengwen
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @HystrixCommand(fallbackMethod = "getDefaultName")
    public String getName(int id) {
        logger.info("getting user name");
        if (id < 0) {
            throw new RuntimeException("id is less than 0");
        }
        return "James";
    }

    public String getDefaultName(int id) {
        return "Alfred";
    }
}
