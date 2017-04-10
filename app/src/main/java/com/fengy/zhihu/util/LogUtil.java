package com.fengy.zhihu.util;

import android.util.Log;

/**
 * Created by fengyun on 2016/10/31.
 */

public class LogUtil {
    private static boolean debug = true;

    public static void e(String tag, String msg) {
        if (debug) {
            Log.e(tag, msg);
        }
    }
}
