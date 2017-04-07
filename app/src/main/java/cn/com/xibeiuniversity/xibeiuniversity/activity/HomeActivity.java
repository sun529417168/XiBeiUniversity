package cn.com.xibeiuniversity.xibeiuniversity.activity;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.adapter.HomeAdapter;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseActivity;
import cn.com.xibeiuniversity.xibeiuniversity.bean.HomeBean;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyUtils;
import cn.com.xibeiuniversity.xibeiuniversity.utils.ToastUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;

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
    private int image[] = {R.mipmap.home_task, R.mipmap.home_notice, R.mipmap.home_problem, R.mipmap.home_statistical, R.mipmap.home_xungeng, R.mipmap.home_anwen};
    private ArrayList<HomeBean> homeArrayList = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private long exitTime;//上一次按退出键时间
    private static final long TIME = 2000;//双击回退键间隔时间


    @Override
    protected void setView() {
        setContentView(R.layout.activity_home);
    }

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
