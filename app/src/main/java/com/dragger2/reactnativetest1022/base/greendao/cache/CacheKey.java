package com.dragger2.reactnativetest1022.base.greendao.cache;

import android.text.TextUtils;

import java.util.Map;

/**
 * Created by wangru
 * Date: 2018/5/10  17:35
 * mail: 1902065822@qq.com
 * describe:
 */

public class CacheKey {
    public static String getKey(String url) {
        return getKey(url, null, null);
    }

    public static String getKey(String url, Map<String, String> map) {
        return getKey(url, map, null);
    }

    public static String getKey(String url, String token) {
        return getKey(url, null, token);
    }

    public static String getKey(String url, Map<String, String> map, String token) {
        StringBuilder stringBuilder = new StringBuilder(url);
        if (!TextUtils.isEmpty(token)) {
            stringBuilder.append("#token=" + token);
        }
        if (map != null) {
            for (String key : map.keySet()) {
                stringBuilder.append("#" + key + "=" + map.get(key));
            }
        }
        return stringBuilder.toString();
    }
}
