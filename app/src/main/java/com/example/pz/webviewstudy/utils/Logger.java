package com.example.pz.webviewstudy.utils;

import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @ClassName: Logger
 * @Description: 日志打印
 */
public class Logger {

    private static boolean SHOW_LOG = true;

    /**
     * 输出info级别的log信息，log中的tag和msg可以传任意对象
     * 默认的Tag为Logger.class 类名
     */
    public static void i(@Nullable Object objMsg) {
        if (SHOW_LOG) {
            String tag = convertStringTag("pz");
            String msg = convertStringMsg(objMsg);
            Log.i(tag, msg);
        }
    }

    /**
     * 把Object类型的tag转换为String类型的tag
     */
    private static String convertStringTag(Object objTag) {
        String tag;
        if (objTag == null) {
            tag = "pz";
        } else if (objTag instanceof String) {
            tag = (String) objTag;
        } else if (objTag instanceof Class) {
            tag = ((Class<?>) objTag).getSimpleName();    // 如果objTag不是String，则取它的类名
        } else {
            tag = objTag.getClass().getSimpleName();    // 取类名
        }
        return tag;
    }

    /**
     * 把对象类型的Msg转换为String类型的Msg
     */
    private static String convertStringMsg(Object objMsg) {
        String msg;
        if (objMsg == null) {
            msg = "nullMsg";
        } else {
            msg = objMsg.toString();
        }
        return msg;
    }

    /**
     * 输出info级别的log信息，log中的tag和msg可以传任意对象
     */
    public static void i(@Nullable Object objTag, @Nullable Object objMsg) {
        if (SHOW_LOG) {
            String tag = convertStringTag(objTag);
            String msg = convertStringMsg(objMsg);
            Log.i(tag, msg);
        }
    }

    public static void e(Object objTag, Object objMsg, Throwable e) {
        if (SHOW_LOG) {
            String tag = convertStringTag(objTag);
            String msg = convertStringMsg(objMsg);
            Log.e(tag, msg, e);
        }
    }

    public static void e(Object objTag, Object objMsg) {
        if (SHOW_LOG) {
            String tag = convertStringTag(objTag);
            String msg = convertStringMsg(objMsg);
            Log.e(tag, msg);
        }
    }

    public static void d(Object objTag, Object objMsg) {
        if (SHOW_LOG) {
            String tag = convertStringTag(objTag);
            String msg = convertStringMsg(objMsg);
            Log.d(tag, msg);
        }
    }

    public static void w(Object objTag, Object objMsg) {
        if (SHOW_LOG) {
            String tag = convertStringTag(objTag);
            String msg = convertStringMsg(objMsg);
            Log.w(tag, msg);
        }
    }
}
