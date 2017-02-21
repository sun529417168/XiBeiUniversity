package cn.com.xibeiuniversity.xibeiuniversity.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Vector;

import static android.R.attr.path;
import static android.content.Context.LOCATION_SERVICE;

/**
 * 文件名：MyUtils
 * 描    述：工具类
 * 作    者：stt
 * 时    间：2017.01.04
 * 版    本：V1.0.0
 */

public class MyUtils {
    /**
     * 方法名：getFromAssets
     * 功    能：获取assets的数据
     * 参    数：Context context,String fileName
     * 返回值：String
     */
    public static String getFromAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 方法名：GetVideoFileName
     * 功    能：获取SD卡目录下的子目录，循环取出
     * 参    数：Context context,String fileName
     * 返回值：String
     */
    public static Vector<String> getVideoFileName(String fileAbsolutePath) {
        Vector<String> vecFile = new Vector<String>();
        File file = new File(fileAbsolutePath);
        // 判断文件目录是否存在
        if (!file.exists()) {
            file.mkdir();
        }
        File[] subFile = file.listFiles();
        for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
            // 判断是否为文件夹
            if (!subFile[iFileLength].isDirectory()) {
                String filename = subFile[iFileLength].getName();
                // 判断是否为xlsx结尾
//                if (filename.trim().toLowerCase().endsWith(".xlsx")) {
                vecFile.add(filename);
//                }
            }
        }
        return vecFile;
    }

    /**
     * 方法名：gpsIsOPen
     * 功    能：判断GPS是否打开
     * 参    数：Context context
     * 返回值：boolean
     * 作    者：stt
     * 时    间：2017.2.21
     */
    public static boolean gpsIsOPen(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps) {
            return true;
        }
        return false;
    }

    /**
     * 方法名：toggleGPS
     * 功    能：自动打开GPS
     * 参    数：Context context
     * 返回值：
     * 作    者：stt
     * 时    间：2017.2.21
     */
    public static void toggleGPS(Context context) {
        Intent gpsIntent = new Intent();
        gpsIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        gpsIntent.addCategory("android.intent.category.ALTERNATIVE");
        gpsIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, gpsIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
}
