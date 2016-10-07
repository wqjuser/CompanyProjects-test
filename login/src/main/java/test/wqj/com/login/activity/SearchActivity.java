package test.wqj.com.login.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.wqj.com.login.R;
import test.wqj.com.login.adapter.MyAdapter_sea_dd_list;
import test.wqj.com.login.adapter.MyAdapter_sea_jyjl_list;
import test.wqj.com.login.adapter.MyAdapter_sea_jyjl_list1;
import test.wqj.com.login.adapter.MyAdapter_sea_kc_list;
import test.wqj.com.login.utils.MyGridView;
import test.wqj.com.login.utils.MyListView;

public class SearchActivity extends AppCompatActivity {
    private MyListView sea_kc_listview;
    private MyListView sea_dd_listview;
    private MyListView sea_dd_listview1;
    private MyListView sea_jyjl_listview1;
    private MyListView sea_jyjl_listview;
    private MyGridView sea_yq_gridview;
    private SimpleAdapter sim_adapter;
    private List<Map<String, Object>> data_list;
    private int[] icon = {R.drawable.yq, R.drawable.yq,
            R.drawable.yq, R.drawable.yq, R.drawable.yq, R.drawable.yq, R.drawable.yq, R.drawable.yq};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_search);
        sea_kc_listview=(MyListView)findViewById(R.id.sea_kc_listview);
        sea_dd_listview=(MyListView)findViewById(R.id.sea_dd_listview);
        sea_dd_listview1=(MyListView)findViewById(R.id.sea_dd_listview1);
        sea_jyjl_listview1=(MyListView)findViewById(R.id.sea_jyjl_listview);
        sea_jyjl_listview=(MyListView)findViewById(R.id.sea_jyjl_listview1);
        sea_yq_gridview=(MyGridView)findViewById(R.id.sea_yq_gridview);
        sea_kc_listview.setAdapter(new MyAdapter_sea_kc_list(this));
        sea_jyjl_listview1.setAdapter(new MyAdapter_sea_jyjl_list(this));
        sea_jyjl_listview.setAdapter(new MyAdapter_sea_jyjl_list1(this));
        sea_dd_listview.setAdapter(new MyAdapter_sea_dd_list(this));
        sea_dd_listview1.setAdapter(new MyAdapter_sea_dd_list(this));
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        getData();
        //新建适配器
        String[] from = {"image", "nameOfYq", "price", "re_price"};
        int[] to = {R.id.grid_imv, R.id.grid_name_yq, R.id.grid_price, R.id.grid_re_price};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.yq_griditem, from, to);
        //配置适配器
        sea_yq_gridview.setAdapter(sim_adapter);


    }
    public List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("nameOfYq", "乐器名称乐器名称");
            map.put("price", "¥ 15.00");
            map.put("re_price", "30.00");
            data_list.add(map);
        }
        return data_list;
    }
}
