package com.mall.application.dto;

public class UserContext {
    private static final ThreadLocal<UserInfo> user = new ThreadLocal<>();

    public static void set(UserInfo info) {
        user.set(info);
    }

    public static UserInfo get() {
        return user.get();
    }

    public static void clear() {
        user.remove();
    }
}
