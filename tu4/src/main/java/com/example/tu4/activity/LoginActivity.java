package com.example.tu4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.tu4.R;

public class LoginActivity extends AppCompatActivity implements View.OnFocusChangeListener {
    /*
    * 判断是登录还是注册，默认的是注册，就是注册时true，登录时false
    * */
    Boolean judgeLoginOrRegister = true;
    Boolean judgePasswordShow = true;

    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    @BindView(R.id.edtTel)
    EditText edtTel;
    @BindView(R.id.textview_line_Tel)
    TextView textviewLineTel;

    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.imgShowPassword)
    ImageView imgShowPassword;
    @BindView(R.id.textview_line_password)
    TextView textviewLinePassword;

    @BindView(R.id.edtVerification)
    EditText edtVerification;
    @BindView(R.id.btnGetVertification)
    Button btnGetVertification;
    @BindView(R.id.RalativelayoutVertifycation)
    RelativeLayout RalativelayoutVertifycation;
    @BindView(R.id.textview_line_vertification)
    TextView textviewLineVertification;
    @BindView(R.id.btnLoginOrRegister)
    Button btnLoginOrRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        edtTel.setOnFocusChangeListener(this);
        edtPassword.setOnFocusChangeListener(this);
        edtVerification.setOnFocusChangeListener(this);
    }

    @OnClick({R.id.btnLogin, R.id.btnRegister, R.id.imgShowPassword, R.id.btnGetVertification, R.id.btnLoginOrRegister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                switchLoginOrRegister(R.drawable.shape, R.drawable.shape2, View.INVISIBLE, R.string.login, false);
                break;
            case R.id.btnRegister:
                switchLoginOrRegister(R.drawable.shape2, R.drawable.shape, View.VISIBLE, R.string.register, true);
                break;
            case R.id.imgShowPassword:
                switchPasswordShowOrHide();
                break;
            case R.id.btnGetVertification:
                break;
            case R.id.btnLoginOrRegister:
                judgeLoginOrRegister();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edtTel:
                controlLineShowWhite(textviewLineTel, hasFocus);
                break;
            case R.id.edtPassword:
                controlLineShowWhite(textviewLinePassword, hasFocus);
                break;
            case R.id.edtVerification:
                controlLineShowWhite(textviewLineVertification, hasFocus);
                break;
        }
    }

    /*
     * 判断密码和电话号码,是不是符合要求
     * */
    public Boolean judgeTelPassword() {
        Boolean vertification = true;
        String telLenth = edtTel.getText().toString();
        if (!(telLenth.length() == 11)) {
            Toast.makeText(LoginActivity.this, R.string.telWrong, Toast.LENGTH_SHORT).show();
            vertification = false;
        }
        String passwordLenth = edtPassword.getText().toString();
        if (!(passwordLenth.length() >= 6 && passwordLenth.length() <= 12)) {
            Toast.makeText(LoginActivity.this, R.string.passwordWrong, Toast.LENGTH_SHORT).show();
            vertification = false;
        }
        return vertification;
    }

    /*
       * 判断验证码是不是符合规定，如果是返回的结果是true，那就是这个符合要求的。
       * */
    public Boolean judgeVertification() {
        Boolean judge = true;
        String vertificationLenth = edtVerification.getText().toString();
        if (vertificationLenth.length() == 0) {
            Toast.makeText(LoginActivity.this, R.string.verificationNull, Toast.LENGTH_SHORT).show();
            judge = false;
        }
        return judge;
    }


    public void switchPasswordShowOrHide() {
        if (judgePasswordShow) {
            edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        judgePasswordShow = !judgePasswordShow;
    }

    public void switchLoginOrRegister(int loginbg, int registerbg, int visible, int text, Boolean bool) {
        btnLogin.setBackgroundResource(loginbg);
        btnRegister.setBackgroundResource(registerbg);
        RalativelayoutVertifycation.setVisibility(visible);
        textviewLineVertification.setVisibility(visible);
        btnLoginOrRegister.setText(text);
        judgeLoginOrRegister = bool;
    }

    public void judgeLoginOrRegister() {
        if (judgeLoginOrRegister) {//代表当前的是注册页面
            if (judgeTelPassword() && judgeVertification()) {
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.registerSuccess), Toast.LENGTH_SHORT).show();
                Intent intentToSearchactivity = new Intent(LoginActivity.this,SearchActivity.class);
                startActivity(intentToSearchactivity);
            }
        } else {
            if (judgeTelPassword()) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }


    public void controlLineShowWhite(TextView line, Boolean fous) {
        if (fous) {
            line.setBackgroundResource(R.color.sffffff);
        } else {

            line.setBackgroundResource(R.color.s40sffffff);
        }
    }
}
