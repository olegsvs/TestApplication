package com.example.testapp.di;

import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DaggerService {

    public static final String ROOT_SCOPE_NAME = "ROOT_SCOPE";
    public static final String PROFILE_SCOPE_NAME = "PROFILE_SCOPE";
    public static final String PHOTO_SCOPE_NAME = "PHOTO_SCOPE";

    private static Map<String, Object> componentMap = new HashMap<>();

    public static void registerComponent(String keyName, Object daggerComponent) {
        if (getComponent(keyName) == null)
            componentMap.put(keyName, daggerComponent);
    }

    @Nullable
    @SuppressWarnings("uncheked")
    public static <T> T getComponent(String keyName) {
        Object component = componentMap.get(keyName);
        return (T) component;
    }

    @SuppressWarnings("uncheked")
    public static void unregisterComponent(String keyName) {
        if (componentMap.containsKey(keyName)) {
            componentMap.remove(keyName);
        }
    }
}
