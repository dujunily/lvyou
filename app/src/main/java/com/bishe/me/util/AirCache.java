package com.bishe.me.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by AIRY on 2015/12/11.
 */
public class AirCache {
 /**
     * @title Save paramkey in xml
     * @author XXX XXX
     * @version 0.9
     * @param xmlName the name of the xml file.
     * @param paramKey the key of value what you want.
     * @param paramValue the value you want.
     */


public static boolean save1(Context context,final String xmlName,String paramKey,String paramValue){
    SharedPreferences manager;
    try {
        manager =
                context.getSharedPreferences(xmlName, Activity.MODE_PRIVATE); //首先获取一个 SharedPreferences 对象
        manager.edit()
                .putString(paramKey, paramValue).commit();


        return true;
    }
    catch (Exception e){


        return false;
    }
    finally {
        manager=null;
    }

    

}



    public static boolean delAXml(Context context,final String xmlName){
        SharedPreferences manager;
        try {
            manager =
                    context.getSharedPreferences(xmlName, Activity.MODE_PRIVATE); //首先获取一个 SharedPreferences 对象
            manager.edit()
                    .clear().commit();

            File file = new File("/data/data/" + context.getPackageName().toString()
                    + "/shared_prefs", xmlName+".xml");
            if (file.exists()) {
                file.delete();
               // ShowDialog("删除SharedPreferences文件成功");
            }


            return true;
        }
        catch (Exception e){


            return false;
        }
        finally {
            manager=null;
        }


    }

    /**
     * @title Save JSONObject in xml
     * @author XXX XXX
     * */
    public static boolean saveJsonObject(Context context,final String xmlName,String paramKey,JSONObject jsonObject){
        return save1(context,xmlName,paramKey,jsonObject.toString());

    }
    /**
     * @title Save JSONObject in xml
     * @author XXX XXX
     * */
    public static JSONObject getJsonObject(Context context,final String xmlName,String paramKey) throws JSONException {
        String s= get1(context, xmlName, paramKey);

        try {
            JSONObject jsonObject=new JSONObject(s);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("Error","MSKY1226ERROR");
            return jsonObject;
        }

    }


    /**
     * @title Get param in xml
     * @author XXX XXX
     * @version 0.9
     * @param xmlName the name of the xml file.
     * @param paramKey the key of value what you want.
     */
    public static String get1(Context context,final String xmlName,String paramKey){

        SharedPreferences manager;
        try {
            manager =context.getSharedPreferences(xmlName, Activity.MODE_PRIVATE);
            String re=manager.getString(paramKey,"");
            return re;
        }
        catch (Exception e){


            return "MSKY1226ERROR";
        }
        finally {
            manager=null;
        }
}

    /**
     * @title Get APP VERSIONNAME
     * @author XXX XXX
     * @version 0.9
     * @return VERSIONNAME
     * .
     */
    public static String getVersion(Context context)
    {
        try {
            PackageInfo pi=context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "MSKY1226ERROR";
        }
    }


    /**
     * @title Save bitmap in xml
     * @author XXX XXX
     * */
    public static boolean saveBitmap(Context context,final String xmlName,String paramKey,Bitmap bitmap){
        return save1(context,xmlName,paramKey,convertIconToString(bitmap));

    }
    /**
     * @title get bitmap in xml
     * @author XXX XXX
     * */
    public static Bitmap getBitmap(Context context,final String xmlName,String paramKey){

        String s= get1(context, xmlName, paramKey);
       Bitmap b1=convertStringToIcon(s);
        return b1;

    }
    /**
     * 图片转成string
     *
     * @param bitmap
     * @return
     */
    public static String convertIconToString(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] appicon = baos.toByteArray();// 转为byte数组
        return Base64.encodeToString(appicon, Base64.DEFAULT);

    }



    /**
     * string转成bitmap
     *
     * @param st
     */
    public static Bitmap convertStringToIcon(String st)
    {
        // OutputStream out;
        Bitmap bitmap = null;
        try
        {
            // out = new FileOutputStream("/sdcard/aa.jpg");
            byte[] bitmapArray;
            bitmapArray = Base64.decode(st, Base64.DEFAULT);
            bitmap =
                    BitmapFactory.decodeByteArray(bitmapArray, 0,
                            bitmapArray.length);
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            return bitmap;
        }
        catch (Exception e)
        {
            return null;
        }
    }


}
