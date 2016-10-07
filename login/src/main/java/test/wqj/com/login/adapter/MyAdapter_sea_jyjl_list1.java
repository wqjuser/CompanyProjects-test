package test.wqj.com.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import test.wqj.com.login.R;

/**
 * Created by MR.WEN on 2016/10/4.
 */

public class MyAdapter_sea_jyjl_list1 extends BaseAdapter {
    private LayoutInflater mInflater; //得到一个LayoutInfalter对象用来导入布局
    public MyAdapter_sea_jyjl_list1(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return getData().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyAdapter_sea_jyjl_list1.viewHolder vh = new MyAdapter_sea_jyjl_list1.viewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.sea_jyjl_list1_item, null);
            vh.textView = (TextView) convertView.findViewById(R.id.sea_jyjl_kc_name);
            vh.textView1 = (TextView) convertView.findViewById(R.id.sea_jyjl_yq_ks);
            vh.textView2 = (TextView) convertView.findViewById(R.id.sea_jyjl_ls);
            vh.textView3 = (TextView) convertView.findViewById(R.id.rmb_sign);
            vh.textView4 = (TextView) convertView.findViewById(R.id.sea_jyjl_price);
            convertView.setTag(vh);
        }
        vh = (MyAdapter_sea_jyjl_list1.viewHolder) convertView.getTag();
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
        vh.textView.setText(getData().get(position).get("Name").toString());
        vh.textView1.setText(getData().get(position).get("Period").toString());
        vh.textView2.setText(getData().get(position).get("Teacher").toString());
        vh.textView3.setText(getData().get(position).get("RMB").toString());
        vh.textView4.setText(getData().get(position).get("Price").toString());
        return convertView;
    }

    class viewHolder {
        TextView textView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
    }
    /*添加一个得到数据的方法，方便使用*/
    private ArrayList<HashMap<String, Object>> getData() {
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        /*为动态数组添加数据*/
        for (int i = 0; i < 1; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("Name", "课程XXXXX");
            map.put("Period", "6课时");
            map.put("Teacher","老师XXX");
            map.put("RMB", "¥");
            map.put("Price","5000.00");
            listItem.add(map);
        }
        return listItem;
    }
}
