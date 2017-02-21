package cn.com.xibeiuniversity.xibeiuniversity.service;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import cn.com.xibeiuniversity.xibeiuniversity.function.gps.GPSLocationListener;
import cn.com.xibeiuniversity.xibeiuniversity.function.gps.GPSProviderStatus;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.GetGPSInterface;
import cn.com.xibeiuniversity.xibeiuniversity.utils.ToastUtil;

/**
 * 文件名：MyGPSListener
 * 描    述：GPS类
 * 作    者：stt
 * 时    间：2017.2.21
 * 版    本：V1.0.0
 */
public class MyGPSListener implements GPSLocationListener {
    private Context context;
    private GetGPSInterface getGPSInterface;

    public MyGPSListener(Context context) {
        this.context = context;
        getGPSInterface = (GetGPSInterface) context;
    }

    @Override
    public void UpdateLocation(Location location) {
        if (location != null) {
            getGPSInterface.getGPS(location.getLongitude() + "", location.getLatitude() + "");
        }
    }

    @Override
    public void UpdateStatus(String provider, int status, Bundle extras) {
        if ("gps" == provider) {
            Toast.makeText(context, "定位类型：" + provider, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void UpdateGPSProviderStatus(int gpsStatus) {
        switch (gpsStatus) {
            case GPSProviderStatus.GPS_ENABLED:
                ToastUtil.show(context, "GPS开启");
                break;
            case GPSProviderStatus.GPS_DISABLED:
                ToastUtil.show(context, "GPS关闭");
                break;
            case GPSProviderStatus.GPS_OUT_OF_SERVICE:
                ToastUtil.show(context, "GPS不可用");
                break;
            case GPSProviderStatus.GPS_TEMPORARILY_UNAVAILABLE:
                ToastUtil.show(context, "GPS暂时不可用");
                break;
            case GPSProviderStatus.GPS_AVAILABLE:
                ToastUtil.show(context, "GPS可用啦");
                break;
        }
    }
}
