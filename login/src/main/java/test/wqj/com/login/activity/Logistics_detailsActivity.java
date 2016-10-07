package test.wqj.com.login.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import test.wqj.com.login.R;
import test.wqj.com.login.bean.WuLiuData;
import test.wqj.com.login.view.WuliuView;

public class Logistics_detailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        setContentView(R.layout.activity_logistics_details);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.line_layout);

        ArrayList<WuLiuData> datas = new ArrayList<WuLiuData>();

        datas.add(new WuLiuData("商家正在通知快递收货",getFormateTime(new Date(System.currentTimeMillis()))));
        datas.add(new WuLiuData("xxx快递员已取货",getFormateTime(new Date(System.currentTimeMillis() - 5 * 60 * 60 *1000))));
        datas.add(new WuLiuData("xxx城已验收，发往下一个城市斯柯达尽快到我家客服了奥斯卡的今晚就哦啊接",getFormateTime(new Date(System.currentTimeMillis() - 9 * 60 * 60 *1000))));
        datas.add(new WuLiuData("下一个城市已扫描，正在装车，下一站下下一个城市",getFormateTime(new Date(System.currentTimeMillis() - 25 * 60 * 60 *1000))));
        datas.add(new WuLiuData("下下一个城市已扫描，正在装车，下一站下下下一个城市声卡的健康挖积分卡即可",getFormateTime(new Date(System.currentTimeMillis() - 40 * 60 * 60 *1000))));
        datas.add(new WuLiuData("下下下一个城市已收件，正在装车，下一站下下下下一个城市时间多久分都分了开始看对外开放看看反馈的开发款福克斯的那个的扣扣给你",getFormateTime(new Date(System.currentTimeMillis() - 50 * 60 * 60 *1000))));
        datas.add(new WuLiuData("下下下下一个城市已收件，正在分配配送员",getFormateTime(new Date(System.currentTimeMillis() - 50 * 60 * 60 *1000))));
        datas.add(new WuLiuData("正在送货",getFormateTime(new Date(System.currentTimeMillis() - 55 * 60 * 60 *1000))));

        if (datas != null && datas.size() > 0){
            WuliuView wuliuView = new WuliuView(this,datas);
            linearLayout.addView(wuliuView);
        }

    }



    public  String getFormateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
