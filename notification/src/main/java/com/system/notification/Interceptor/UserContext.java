package com.system.notification.Interceptor;

import com.system.notification.model.User;

public class UserContext {
    private User userDetails;

    public User getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(User userDetails) {
        this.userDetails = userDetails;
    }
}
