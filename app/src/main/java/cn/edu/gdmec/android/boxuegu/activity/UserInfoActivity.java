package cn.edu.gdmec.android.boxuegu.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.bean.UserBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.utils.DBUtils;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_nickName,tv_signature,tv_user_name,tv_sex;
    private RelativeLayout rl_nickName,rl_sex,rl_signature,rl_title_bar;
    private static final int CHANGE_NICKNAME = 1;//修改昵称的自定义常量
    private static final int CHANGE_SIGNATURE = 2; //修改个性签名的自定义常量
    private String spUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //从SharedPreferences中获取登录时的用户名
        spUserName = AnalysisUtils.readLoginUserName(this);
        init();
        initData();
        setListener();
    }
    /**
     * 初始化控件
     */
    private void init() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("个人资料");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        rl_nickName = (RelativeLayout) findViewById(R.id.rl_nickName);
        rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        rl_signature = (RelativeLayout) findViewById(R.id.rl_signature);
        tv_nickName = (TextView) findViewById(R.id.tv_nickName);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_signature = (TextView) findViewById(R.id.tv_signature);
    }

    /**
     * 获取数据
     */
    private void initData() {
//        UserBean bean = unll;
//        bean = DBUtils.getInstance(this).getUserInfo(spUserName);
//        //首先判断一下数据库是否有数据
//        if (bean == null){
//            bean = new UserName();
//            bean.userName = spUserName;
//            bean.nickName = "问答精灵";
//            bean.sex = "男";
//            bean.signature = "问答精灵";
//            //保存用户信息到数据库
//            DBUtils.getInstance(this).savedUserInfo(bean);
//        }
//        setValue(bean);
    }

    private void setListener() {

    }





    @Override
    public void onClick(View view) {

    }
}
