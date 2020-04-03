package org.allen.user.bus.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * @author Zhou Zhengwen
 */
public class UserUpdateEvent extends RemoteApplicationEvent{

    private String newName;

    public UserUpdateEvent() {
    }

    public UserUpdateEvent(String newName, String originService, String destinationService) {
        super(newName, originService, destinationService);
        this.newName = newName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
