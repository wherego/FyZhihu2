package com.fengy.zhihu.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by fengyun on 2017/4/9.
 */

public class NetUtil {

    private NetUtil() {
    }

    /**
     * 判断网络是否可用
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    //当前网络是连接的
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        //当前所连接的网络可用
                        return true;

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 宽带联网
     * @return
     */
    public static boolean isNetworkConnected() {
        if (AppContextUtil.getInstance() != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) AppContextUtil.getInstance()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * wifi联网
     * @return
     */
    public static boolean isWifiConnected() {
        if (AppContextUtil.getInstance() != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) AppContextUtil.getInstance()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 数据联网
     * @return
     */
    public static boolean isMobileConnected() {
        if (AppContextUtil.getInstance() != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) AppContextUtil.getInstance()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取联网方式
     * @return
     */
    public static int getConnectedType() {
        if (AppContextUtil.getInstance() != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) AppContextUtil.getInstance()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }
}

