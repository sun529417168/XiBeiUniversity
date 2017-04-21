package cn.com.xibeiuniversity.xibeiuniversity.activity;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.HomeAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseActivity;
import cn.com.xibeiuniversity.xibeiuniversity.bean.HomeBean;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyUtils;
import cn.com.xibeiuniversity.xibeiuniversity.utils.SharedUtil;
import cn.com.xibeiuniversity.xibeiuniversity.utils.ToastUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baidu.mapapi.SDKInitializer;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 文件名：HomeActivity
 * 描    述：首页按功能分类
 * 作    者：stt
 * 时    间：2017.04.06
 * 版    本：V1.1.2
 */

public class HomeActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private int image[] = {R.mipmap.home_task, R.mipmap.home_notice, R.mipmap.home_problem, R.mipmap.home_statistical, R.mipmap.home_xungeng, R.mipmap.home_anwen, R.mipmap.ic_map};
    private ArrayList<HomeBean> homeArrayList = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private long exitTime;//上一次按退出键时间
    private static final long TIME = 2000;//双击回退键间隔时间

    /**
     * 构造广播监听类，监听 SDK key 验证以及网络异常广播
     */
    public class SDKReceiver extends BroadcastReceiver {

        public void onReceive(Context context, Intent intent) {
            String s = intent.getAction();
            Log.d("LoginActivity", "action: " + s);
            TextView text = (TextView) findViewById(R.id.baiduMap);
            text.setTextColor(Color.RED);
            if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
                text.setText("key 验证出错! 错误码 :" + intent.getIntExtra
                        (SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE, 0)
                        + " ; 请在 AndroidManifest.xml 文件中检查 key 设置");
            } else if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
                text.setText("key 验证成功! 功能可以正常使用");
                text.setTextColor(Color.BLACK);
            } else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
                text.setText("网络出错");
            }
        }
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_home);
    }

    private SDKReceiver mReceiver;

    @Override
    protected void setDate(Bundle savedInstanceState) {
        ArrayList<HomeBean> homeList = (ArrayList<HomeBean>) JSON.parseArray(MyUtils.getFromAssets(this, "home.txt"), HomeBean.class);
        for (int i = 0; i < homeList.size(); i++) {
            if (homeList.get(i).isIsTrue()) {
                HomeBean homeBean = new HomeBean();
                homeBean.setId(homeList.get(i).getId());
                homeBean.setName(homeList.get(i).getName());
                homeBean.setImageView(image[i]);
                homeArrayList.add(homeBean);
            }
        }

        // 注册 SDK 广播监听者
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mReceiver = new SDKReceiver();
        registerReceiver(mReceiver, iFilter);

    }

    @Override
    protected void init() {
        gridView = (GridView) findViewById(R.id.home_gridView);
        homeAdapter = new HomeAdapter(this, homeArrayList);
        gridView.setAdapter(homeAdapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in = new Intent(this, MainActivity.class);
        if (homeArrayList.get(position).getId() == 107) {
            Intent intent = new Intent(this, LocationActivity.class);
            startActivity(intent);
        } else if (SharedUtil.getBoolean(this, "isLogin", false)) {
            switch (homeArrayList.get(position).getId()) {
                case 101:
                    in.putExtra("index", 0);
                    startActivity(in);
                    break;
                case 102:
                    in.putExtra("index", 1);
                    startActivity(in);
                    break;
                case 103:
                    in.putExtra("index", 2);
                    startActivity(in);
                    break;
                case 104:
                    in.putExtra("index", 3);
                    startActivity(in);
                    break;
                case 105:

                    break;
                case 106:

                    break;
                case 107:
                    Intent intent = new Intent(this, LocationActivity.class);
                    startActivity(intent);
                    break;
            }
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > TIME) {
                ToastUtil.show(this, "再按一次返回键退出");
                exitTime = System.currentTimeMillis();
                return true;
            } else {
                XiBeiApp.exit();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
