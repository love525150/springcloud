package org.allen.user.bus.listener;

import org.allen.user.bus.event.UserUpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Zhou Zhengwen
 */
@Component
public class UserUpdateEventListener implements ApplicationListener<UserUpdateEvent> {
    private static final Logger logger = LoggerFactory.getLogger(UserUpdateEventListener.class);

    @Override
    public void onApplicationEvent(UserUpdateEvent userUpdateEvent) {
        logger.info("successfully update user with name:{}", userUpdateEvent.getNewName());
    }
}
