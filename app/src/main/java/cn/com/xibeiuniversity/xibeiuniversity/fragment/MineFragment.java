package cn.com.xibeiuniversity.xibeiuniversity.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.activity.UpdatePasswordActivity;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseFragment;
import cn.com.xibeiuniversity.xibeiuniversity.bean.PersonBean;

/**
 * 文件名：MineFragment
 * 描    述：个人中心界面
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class MineFragment extends BaseFragment implements View.OnClickListener{
    private Context context;
    private TextView titleName;//标题名称
    private PersonBean mineBean;
    private RelativeLayout updatePassword;
    /**
     * 姓名，手机号，身份证号
     */
    private TextView nameText, phoneText, userIDText;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_mine, null);
        return view;
    }

    @Override
    protected void setDate() {
    }

    @Override
    protected void init(View rootView) {
        titleName = (TextView) rootView.findViewById(R.id.title_name);
        titleName.setText("我的");
        nameText = (TextView) rootView.findViewById(R.id.mine_name);
        phoneText = (TextView) rootView.findViewById(R.id.mine_phone);
        userIDText = (TextView) rootView.findViewById(R.id.mine_userID);

        updatePassword = (RelativeLayout) rootView.findViewById(R.id.mine_updatePassword);
        updatePassword.setOnClickListener(this);

        nameText.setText("张三");
        phoneText.setText("18514235676");
        userIDText.setText("130628199101064112");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_updatePassword:
                Intent intent = new Intent(context, UpdatePasswordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
