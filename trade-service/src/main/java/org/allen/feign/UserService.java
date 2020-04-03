package org.allen.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service")
public interface UserService {

    @RequestMapping(method = RequestMethod.GET, value = "/user/name")
    String name(@RequestParam("id") int id);
}
