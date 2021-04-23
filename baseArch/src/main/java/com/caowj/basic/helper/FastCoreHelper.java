package com.caowj.basic.helper;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <pre>
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2021/4/23 11:03
 * </pre>
 */

public class FastCoreHelper {
    /**
     * 通过泛型获取class类型
     *
     * @param object
     * @return
     */
    public static Class getViewModelClass(@NonNull Object object) {
        if (object != null && (object instanceof Activity || object instanceof Fragment || object instanceof Dialog)) {
            Class<?> objClass = object.getClass();

            Type type = object.getClass().getGenericSuperclass();
            ParameterizedType paramtype = null;
            try {
                paramtype = (ParameterizedType) type;
            } catch (ClassCastException e) {
                return null;
            }
            Type[] typeArguments = paramtype.getActualTypeArguments();
            if (typeArguments != null && typeArguments.length > 1) {
                return (Class) typeArguments[1];
            }

        }
        return null;
    }
}
