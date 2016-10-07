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

public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button register;
    private TextView log_account;
    private TextView log_password;
    private EditText username;
    private EditText password;
    private ImageButton iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.log_bt);
        register = (Button) findViewById(R.id.re_register);
        log_account = (TextView) findViewById(R.id.log_account);
        log_password = (TextView) findViewById(R.id.log_password);
        username = (EditText) findViewById(R.id.Login_editText);
        password = (EditText) findViewById(R.id.editText2);
        iv = (ImageButton) findViewById(R.id.imageButton);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    log_account.setBackgroundResource(R.drawable.textview_focused);
                } else {
                    log_account.setBackgroundResource(R.drawable.textview_unfocused);
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    log_password.setBackgroundResource(R.drawable.textview_focused);
                } else {
                    log_password.setBackgroundResource(R.drawable.textview_unfocused);
                }
            }
        });
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int eventaction = event.getAction();
                switch (eventaction) {
                    case MotionEvent.ACTION_DOWN: {
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        password.setSelection(password.getText().toString().length());
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        password.setSelection(password.getText().toString().length());
                        break;
                    }
                }
                return false;
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.length() <= 0 || password.length() <= 0) {
                    Toast.makeText(MainActivity.this, "账号或者密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(MainActivity.this, "密码不能小于六位", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, PersonActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
