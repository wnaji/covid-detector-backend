package be.ipl.pfe.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    public static Map<String, Object> objectWithTokenToJson(String key, Object value, String token) {
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put(key, value);
        return response;
    }

    public static Map<String, String> stringToJson(String key, String value) {
        return Collections.singletonMap(key, value);
    }

    public static Map<String, String> errorToJson(String error) {
        return Collections.singletonMap("error", error);
    }

}
