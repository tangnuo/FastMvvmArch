package com.caowj.fastdev.util;

import android.text.TextUtils;

import com.caowj.lib_logs.LegoLog;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2020/4/7 16:39
 * </pre>
 */

public class GsonUtil {
    /**
     * 把modelA对象的属性值赋值给bClass对象的属性。
     *
     * @param modelA
     * @param bClass
     * @param <T>
     * @return
     */
    public static <A, T> T modelAconvertoB(A modelA, Class<T> bClass) {
        try {
            Gson gson = new Gson();
            String gsonA = gson.toJson(modelA);
            T instanceB = gson.fromJson(gsonA, bClass);
            return instanceB;
        } catch (Exception e) {
            return null;
        }
    }

    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }

    /**
     * Gson泛型解析Json报错：com.google.gson.internal.LinkedTreeMap cannot be cast to test.UserBean
     *
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> getObjectList(String jsonString, Class<T> cls) {
        if (TextUtils.isEmpty(jsonString)) {
            LegoLog.w("json转换异常，jsonString is null");
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        try {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(jsonString).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            LegoLog.e(e.getMessage(), e);
        }
        return list;
    }

    public static <T> Set<T> getObjectSet(String jsonString, Class<T> cls) {
        Set<T> list = new HashSet<>();
        try {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(jsonString).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            LegoLog.e(e.getMessage(), e);
        }
        return list;
    }

    public static <T> T fromJson(String json, TypeToken<T> token) {
        return new Gson().fromJson(json, token.getType());
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }

    /**
     * 转成json类型
     *
     * @param object
     * @return
     */
    public static String toGsonString(Object object) {
        String gsonString;
        Gson gson = new Gson();
        gsonString = gson.toJson(object);
        return gsonString;
    }

    /**
     * 将Object类型的json返回值，转换成 JsonObject
     *
     * @param objectJson
     * @return
     */
    public static JsonObject toJsonObject(Object objectJson) {
        String jsonStr = toGsonString(objectJson);
        if (TextUtils.isEmpty(jsonStr)) {
            throw new RuntimeException("json字符串");
        }

        JsonElement jsonElement = new JsonParser().parse(jsonStr);
        if (jsonElement.isJsonNull()) {
            throw new RuntimeException("得到的jsonElement对象为空");
        }

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        return jsonObject;
    }


    /**
     * 转成bean
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = new Gson().fromJson(gsonString, cls);
        return t;
    }

    public static <T> T GsonToBean(JsonObject jsonObject, Class<T> cls) {
        String gsonString = String.valueOf(jsonObject);//可以
//        String gsonString2 = toGsonString(jsonObject);//可以
//        String gsonString3 = jsonObject.toString();//不支持这种写法

        return GsonToBean(gsonString, cls);
    }

}
