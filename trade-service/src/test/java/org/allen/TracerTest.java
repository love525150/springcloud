package org.allen;

import brave.ScopedSpan;
import brave.Tracer;
import com.netflix.discovery.converters.Auto;
import org.allen.feign.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Zhou Zhengwen
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TradeServiceApplication.class)
public class TracerTest {
    private static final Logger logger = LoggerFactory.getLogger(TracerTest.class);

    @Autowired
    private Tracer tracer;
    @Autowired
    private UserService userService;

    @Test
    public void testTracer() {
        ScopedSpan span = tracer.startScopedSpan("encode");
        try {
            logger.info("success");
            System.out.println();
        } catch (Throwable e) {
            span.error(e);
        } finally {
            span.finish();
            System.out.println();
        }
    }

}
