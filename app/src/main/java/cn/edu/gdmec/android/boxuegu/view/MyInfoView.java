package cn.edu.gdmec.android.boxuegu.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.activity.LoginActivity;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

/**
 * Created by student on 17/12/27.
 */

public class MyInfoView {
    private Context mContext;
    private final LayoutInflater mInflater;
    private View mCurrentView;
    private LinearLayout ll_head;
    private ImageView iv_head_icon;
    private RelativeLayout rl_course_history;
    private  RelativeLayout rl_setting;
    private TextView tv_user_name;

    public MyInfoView(Context context){
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public View getView(){
        if (mCurrentView == null){
            createView();
        }
        return mCurrentView;
    }

    private void createView() {
        initView();
    }

    private void initView(){
        mCurrentView = mInflater.inflate(R.layout.main_view_myinfo, null);
        ll_head = (LinearLayout) mCurrentView.findViewById(R.id.ll_head);
        iv_head_icon = (ImageView) mCurrentView.findViewById(R.id.iv_head_icon);

        rl_course_history = (RelativeLayout) mCurrentView.findViewById(R.id.rl_course_history);
        rl_setting = (RelativeLayout) mCurrentView.findViewById(R.id.rl_setting);
        tv_user_name = (TextView) mCurrentView.findViewById(R.id.tv_user_name);
        mCurrentView.setVisibility(View.VISIBLE);
        setLoginParams(readLoginStatus());

        ll_head.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //判断是否登录
                if (readLoginStatus()) {
                    //跳转到个人资料界面
                }

//                }else {
//                    Intent intent = new Intent(mContext, LoginActivity.class);
//                    ((Activity) mContext.startActivityForResult(intent, 1));
//                }
            }
        });

        rl_course_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (readLoginStatus()){
                    // 跳转到播放记录页面

                }else {
                    Toast.makeText(mContext, "您还未登录,请先登录", Toast.LENGTH_LONG).show();
                }
            }
        });
        rl_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (readLoginStatus()){
                    // 跳转到设置页面

                }else {
                    Toast.makeText(mContext, "您还未登录,请先登录", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void setLoginParams(boolean isLogin) {
        if (isLogin){
            tv_user_name.setText(AnalysisUtils.readLoginUserName(mContext));
        }else {
            tv_user_name.setText("点击登录");
        }
    }

    private boolean readLoginStatus(){
        SharedPreferences sp = mContext.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }

    public void showView() {
        if (mCurrentView == null){
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }
}
