package cn.com.xibeiuniversity.xibeiuniversity.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.xibeiuniversity.xibeiuniversity.R;
import cn.com.xibeiuniversity.xibeiuniversity.activity.UpdatePasswordActivity;
import cn.com.xibeiuniversity.xibeiuniversity.base.BaseFragment;
import cn.com.xibeiuniversity.xibeiuniversity.bean.PersonBean;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.PersonInfoInterface;
import cn.com.xibeiuniversity.xibeiuniversity.interfaces.ProblemListInterface;
import cn.com.xibeiuniversity.xibeiuniversity.utils.DataCleanManager;
import cn.com.xibeiuniversity.xibeiuniversity.utils.DialogUtils;
import cn.com.xibeiuniversity.xibeiuniversity.utils.MyRequest;
import cn.com.xibeiuniversity.xibeiuniversity.utils.SharedUtil;

/**
 * 文件名：MineFragment
 * 描    述：个人中心界面
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class MineFragment extends BaseFragment implements View.OnClickListener, PersonInfoInterface {
    private Context context;
    private TextView titleName;//标题名称
    private PersonBean mineBean;
    private RelativeLayout updatePassword;
    /**
     * 姓名，手机号，身份证号
     */
    private TextView nameText, phoneText, userIDText;
    private TextView userNameText, workNoText;
    private RelativeLayout clearCacheLayout;
    private TextView cacheSize;
    private Button exitBtn;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_mine, null);
        return view;
    }

    @Override
    protected void setDate() {
        MyRequest.personInfoRequest(context, this, SharedUtil.getString(context, "PersonID"));
    }

    @Override
    protected void init(View rootView) {
        titleName = (TextView) rootView.findViewById(R.id.title_name);
        titleName.setText("我的");
        userNameText = (TextView) rootView.findViewById(R.id.mine_titleUsername);
        workNoText = (TextView) rootView.findViewById(R.id.mine_titleWorkNo);

        nameText = (TextView) rootView.findViewById(R.id.mine_name);
        phoneText = (TextView) rootView.findViewById(R.id.mine_phone);
        userIDText = (TextView) rootView.findViewById(R.id.mine_userID);

        updatePassword = (RelativeLayout) rootView.findViewById(R.id.mine_updatePassword);
        updatePassword.setOnClickListener(this);
        clearCacheLayout = (RelativeLayout) rootView.findViewById(R.id.mine_cache_Layout);
        clearCacheLayout.setOnClickListener(this);
        cacheSize = (TextView) rootView.findViewById(R.id.mine_cacheSize);
        try {
            cacheSize.setText(DataCleanManager.getTotalCacheSize(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
        exitBtn = (Button) rootView.findViewById(R.id.exit_button);
        exitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_updatePassword:
                Intent intent = new Intent(context, UpdatePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_cache_Layout:
                DialogUtils.clearData(context, cacheSize);
                break;
            case R.id.exit_button:
                DialogUtils.exit(getActivity());
                break;
        }
    }

    @Override
    public void getPersonInfo(PersonBean personBean) {
        userNameText.setText("用户名:" + SharedUtil.getString(context, "userName"));
        workNoText.setText("工号:" + personBean.getWorkNO());
        nameText.setText(personBean.getName());
        phoneText.setText(personBean.getPhone());
        userIDText.setText(personBean.getIDCard());
    }

}
