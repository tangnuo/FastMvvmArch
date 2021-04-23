package com.caowj.basic.helper;

import android.app.Activity;
import android.util.Log;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 统一管理Activity的堆栈
 * <p>
 * Created by huangyin on 2018-05-28
 */
public class FastActivityManager {
    private static final String tag = FastActivityManager.class.getSimpleName();
    private static LinkedList<Activity> activities = new LinkedList<>();

    /**
     * create一个Activity
     *
     * @param activity
     */
    public static synchronized void addActivity(Activity activity) {
        if (activity != null) {
            activities.add(activity);
            Log.i(tag, "[add] " + activity.getClass().getName());
        }
    }

    /**
     * destroy掉一个Activity
     *
     * @param activity
     */
    public static synchronized void removeActivity(Activity activity) {
        if (activity != null) {
            boolean rst = activities.remove(activity);
            if (rst) {
                Log.i(tag, "[del] " + activity.getClass().getName());
            }
        }
    }

    /**
     * finish掉所有的Activity
     */
    public static synchronized void clear() {
        while (activities.size() > 0) {
            activities.pollFirst().finish();
        }

        Log.i(tag, "[clear]");
    }

    /**
     * finish掉最后一个Activity之前的所有Activity
     */
    public static synchronized void clearActivitiesExceptLast() {

        while (activities.size() > 1) {
            activities.pollFirst().finish();
        }

        Log.i(tag, "[clearActivitiesExceptLast]");
    }

    /**
     * 跳转到最后一次出现的指定Activity那里<p>
     * 指定的Activity之上的Activity将被finish
     * 例如：A->B->C->B->D->E | toLastIndexOf(B.class) | A->B->C->B
     */
    public static synchronized void toLastIndexOf(Class<? extends Activity> activityClass) {
        boolean findLastActivity = false;
//        int index = 0;
//        for (int i = activities.size() - 1; i >= 0; i--) {
//            Activity activity = activities.get(i);
//            if (activity.getClass().equals(activityClass)) {
//                findLastActivity = true;
//                index = i;
//                break;
//            }
//        }
//
//        if (findLastActivity) {
//            Activity activity = null;
//            int removeIndex = index + 1;
//            while (activities.size() > removeIndex) {
//                activity = activities.remove(removeIndex);
//                Log.i(tag, "[del] " + activity.getClass().getName());
//                activity.finish();
//            }
//        }
        if (activities.isEmpty()) {
            return;
        }
        // 如果指定activity在栈顶，则什么都不做
        if (activities.peekLast().getClass().equals(activityClass)) {
            return;
        }
        // 倒序迭代器
        Iterator<Activity> iterator = activities.descendingIterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getClass().equals(activityClass)) {
                findLastActivity = true;
                break;
            }
        }

        if (findLastActivity) {
            Activity activity = null;
            while (activities.peekLast() != null && !activityClass.equals(activities.peekLast().getClass())) {
                activity = activities.pollLast();
                activity.finish();
                Log.i(tag, "[del] " + activity.getClass().getName());
            }
        }
    }

    /**
     * 跳转到第一次出现的指定Activity那里<p>
     * 指定的Activity之上的Activity将被finish
     * 例如：A->B->C->B->D->E | toFirstIndexOf(B.class) | A->B
     */
    public static synchronized void toFirstIndexOf(Class<? extends Activity> activityClass) {
        if (activities.isEmpty()) {
            return;
        }
        boolean findFirstActivity = false;
        Activity activity = null;
        Iterator<Activity> iterator = activities.listIterator();
        while (iterator.hasNext()) {
            activity = iterator.next();
            if (!findFirstActivity && activity.getClass().equals(activityClass)) {
                if (iterator.hasNext()) {
                    findFirstActivity = true;
                } else {
                    // 第一次出现的activity就在栈顶,则什么都不做，跳出循环
                    break;
                }
            } else if (findFirstActivity) {
                activity.finish();
                iterator.remove();
            }
        }


//        for (int i = 0; i < activities.size(); i++) {
//            Activity activity = activities.get(i);
//            if (!findFirstActivity && activity.getClass().equals(activityClass)) {
//                findFirstActivity = true;
//                if (i == activities.size() - 1) {// 第一次出现的activity就在栈顶,则什么都不做，跳出循环
//                    break;
//                }
//                continue;
//            }
//            if (findFirstActivity) {
//                Log.i(tag, "[del] " + activity.getClass().getName());
//                activities.remove(i);
//                activity.finish();
//                i--;
//            }
//        }

    }

    /**
     * 获取栈顶activity
     *
     * @return
     */
    public static synchronized Activity getTopActivity() {
        return activities.getLast();
    }

    /**
     * 获取栈底activity
     *
     * @return
     */
    public static synchronized Activity getBottomActivity() {
        return activities.getFirst();
    }


}
