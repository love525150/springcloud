package org.allen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author Zhou Zhengwen
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TradeServiceApplication.class)
public class RestTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test() {
        String ok = restTemplate.getForObject("http://user-service/user/update", String.class, "name", "Mike");
        System.out.println(ok);
    }
}
