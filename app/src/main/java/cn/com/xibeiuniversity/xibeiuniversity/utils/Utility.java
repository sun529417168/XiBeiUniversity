package cn.com.xibeiuniversity.xibeiuniversity.utils;

import java.text.DecimalFormat;

/**
 * 文件名：Utility
 * 描    述：保留小数
 * 作    者：stt
 * 时    间：2017.3.7
 * 版    本：V1.0.3
 */
public class Utility {
    /**
     * 格式化浮点数据，保留1位小数
     */
    public static String formatFloat(double value) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(value);
    }

    public static int getScale(float value) {
        if (value >= 1 && value < 10) {
            return 0;
        }

        if (value >= 10) {
            return 1 + getScale(value / 10);
        } else {
            return getScale(value * 10) - 1;
        }
    }
}
