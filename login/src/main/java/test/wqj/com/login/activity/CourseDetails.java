package test.wqj.com.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import test.wqj.com.login.R;
import test.wqj.com.login.utils.CircularImage;

public class CourseDetails extends AppCompatActivity {
    private TextView couese_name;
    private TextView leavel;
    private TextView teacher;
    private TextView place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        CircularImage cover_user_photo = (CircularImage) findViewById(R.id.cover_user_photo_kcxq);
        cover_user_photo.setImageResource(R.drawable.touxiang);
        couese_name=(TextView)findViewById(R.id.kcxq_name);
        leavel=(TextView)findViewById(R.id.kcxq_leavel);
        teacher=(TextView)findViewById(R.id.kcxq_teacher);
        place=(TextView)findViewById(R.id.kcxq_place);
        Intent intent=getIntent();
        couese_name.setText(intent.getStringExtra("kc_name"));
        leavel.setText(intent.getStringExtra("lea"));
        teacher.setText(intent.getStringExtra("tea"));
        place.setText(intent.getStringExtra("place"));
    }
}
