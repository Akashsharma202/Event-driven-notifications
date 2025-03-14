package com.system.notification.Interceptor;

import com.system.notification.model.User;

public class ThreadContext {
    private static ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    public static User getUsers() {
        return userContext.get().getUserDetails();
    }

    public static void setUsers(User users) {
        initialize();
        userContext.get().setUserDetails(users);
    }

    public static void clear() {
        initialize();
        userContext.remove();
    }

    private static void initialize() {

        if (userContext.get() == null) {
            userContext.set(new UserContext());
        }
    }
}
