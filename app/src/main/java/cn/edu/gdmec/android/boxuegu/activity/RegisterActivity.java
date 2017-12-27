package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.utils.MD5Utils;

import static cn.edu.gdmec.android.boxuegu.R.layout.activity_register;

public class RegisterActivity extends AppCompatActivity {


    private TextView tv_name_title;
    private TextView tv_back;
    private RelativeLayout rl_title_bar;
    private Button btn_register;
    private EditText et_user_name;
    private EditText et_psw;
    private EditText et_psw_again;
    private String userName;
    private String psw;
    private String pwdAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_register);
        init();
    }

    private void init() {
        //从main_title_bar.xml页面布局中获取对应的UI控件
        tv_name_title = (TextView) findViewById(R.id.tv_main_title);
        tv_name_title.setText("注册");
        tv_back = (TextView) findViewById(R.id.tv_back);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT);
        
        //从activity_register.xml布局中获取对应的UI控件
        btn_register = (Button) findViewById(R.id.btn_register);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_psw = (EditText) findViewById(R.id.et_pwd);
        et_psw_again = (EditText) findViewById(R.id.et_pwd_again);
        tv_back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                RegisterActivity.this.finish();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入在响应控件中的字符串
                getEditString();
                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterActivity.this,"请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActivity.this,"请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(pwdAgain)){
                    Toast.makeText(RegisterActivity.this,"请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!psw.equals(pwdAgain)){
                    Toast.makeText(RegisterActivity.this,"输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                }else if (isExistUserName(userName)){
                    Toast.makeText(RegisterActivity.this,"此账户名已经存在", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(RegisterActivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                    //把用户名和密码保存到sharedpreference里面
                    saveRegisterInfo(userName,psw);
                    //注册成功后把用户名传递到LoginActivity.java中
                    Intent data = new Intent();
                    data.putExtra("userName",userName);
                    setResult(RESULT_OK,data);
                    RegisterActivity.this.finish();
                }
            }
        });
    }

    private void saveRegisterInfo(String userName, String psw) {
        String md5Psw = MD5Utils.md5(psw);//把密码用MD5加密
        //loginInfo是sp的文件名
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();//获取sp的编辑器
        //userName作为key，密码作为value
        editor.putString(userName,md5Psw);
        editor.commit();//提交修改
    }

    /**
     * 从sharedpreferences中读取输入的用户名，判断sharedpreference中是否有此用户名
     */
    private boolean isExistUserName(String userName) {
        boolean has_username = false;
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw = sp.getString(userName,"");
        if (!TextUtils.isEmpty(spPsw)){
            has_username = true;
        }
        return has_username;
    }


    /*＊
     * 获取控件中的字符串
     */
    private void getEditString() {
        userName = et_user_name.getText().toString().trim();
        psw = et_psw.getText().toString().trim();
        pwdAgain = et_psw_again.getText().toString().trim();
    }
}
