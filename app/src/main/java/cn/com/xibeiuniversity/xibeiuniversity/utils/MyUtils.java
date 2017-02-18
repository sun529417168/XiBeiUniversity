package cn.com.xibeiuniversity.xibeiuniversity.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Vector;

import static android.R.attr.path;

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
}
