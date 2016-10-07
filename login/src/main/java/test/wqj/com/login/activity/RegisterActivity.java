package test.wqj.com.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import test.wqj.com.login.R;

public class RegisterActivity extends AppCompatActivity {

    private Button login;
    private Button register1;
    private Button getYzm;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private EditText re_username;
    private EditText re_password;
    private EditText YZM;
    private ImageButton iv;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_register);
        login = (Button) findViewById(R.id.re_login);
        register1 = (Button) findViewById(R.id.re_bt);
        getYzm = (Button) findViewById(R.id.re_getyzm);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.textView3);
        re_username = (EditText) findViewById(R.id.re_account);
        re_password = (EditText) findViewById(R.id.re_password);
        YZM = (EditText) findViewById(R.id.re_yzm);
        iv = (ImageButton) findViewById(R.id.imageButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        re_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    tv1.setBackgroundResource(R.drawable.textview_focused);
                } else {
                    tv1.setBackgroundResource(R.drawable.textview_unfocused);
                }
            }
        });
        re_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    tv2.setBackgroundResource(R.drawable.textview_focused);
                } else {
                    tv2.setBackgroundResource(R.drawable.textview_unfocused);
                }
            }
        });
        YZM.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    tv3.setBackgroundResource(R.drawable.textview_focused);
                } else {
                    tv3.setBackgroundResource(R.drawable.textview_unfocused);
                }
            }
        });
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int eventaction = event.getAction();
                switch (eventaction) {
                    case MotionEvent.ACTION_DOWN: {
                        re_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        re_password.setSelection(re_password.getText().toString().length());
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        re_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        re_password.setSelection(re_password.getText().toString().length());
                        break;
                    }
                }
                return false;
            }
        });
        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (re_username.length() <= 0 || re_password.length() <= 0) {
                    Toast.makeText(RegisterActivity.this, "账号或者密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (re_password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "密码不能小于六位", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "注册成功,即将跳往登录界面", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        getYzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (YZM.length() < 10) {
                    Toast.makeText(RegisterActivity.this, "手机号码格式错误", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.setClass(RegisterActivity.this, TimeSet.class);
                    startActivity(intent);
                }
            }
        });
    }
}
