package com.bishe.me.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;

/**
 * 静态类SysConfig操作系统配置. 提供了读取配置内容和保存等功能.
 */
public class SysConfig {
    private static final String TAG = "[SysConfig]";

    /** 签名认证公钥 */

    //加密方式：not=不加密，password=只加密密码，all=加密工卡号和密码.
    private static final String MD5Style = "all";
    //版本号
    private static int versionCode;
    //版本名称
    private static String versionName;

    /**
     * 功  能：读入SysConfig属性值，并初始化系统配置类SysConfig.
     * 参  数：context
     * 返回值：true，初始化正常。 false， 初始化失败。
     **/
    public static boolean initialize (@Nullable Context context) {
        if (context != null) {
            try {
                PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                // 当前应用的版本名称
                versionName = info.versionName;
                // 当前版本的版本号
                versionCode = info.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功  能：返回加密方式
     * 参  数：无
     * 返回值：加密方式
     **/
    public static String getMD5Style () {
        return MD5Style;
    }

    public static int getVersionCode () {
        return versionCode;
    }

    public static void setVersionCode (int versionCode) {
        SysConfig.versionCode = versionCode;
    }

    public static String getVersionName () {
        return versionName;
    }

    public static void setVersionName (String versionName) {
        SysConfig.versionName = versionName;
    }
}
