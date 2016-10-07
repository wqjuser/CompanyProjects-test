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

public class MyAdapter_sea_dd_list extends BaseAdapter {
    private LayoutInflater mInflater; //得到一个LayoutInfalter对象用来导入布局
    public MyAdapter_sea_dd_list(Context context) {
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
        MyAdapter_sea_dd_list.viewHolder vh = new MyAdapter_sea_dd_list.viewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.sea_dd_list, null);
            vh.textView = (TextView) convertView.findViewById(R.id.sea_time);
            vh.textView1 = (TextView) convertView.findViewById(R.id.sea_name);
            vh.textView2 = (TextView) convertView.findViewById(R.id.sea_position);
            convertView.setTag(vh);
        }
        vh = (MyAdapter_sea_dd_list.viewHolder) convertView.getTag();
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/

        vh.textView.setText(getData().get(position).get("Time").toString());
        vh.textView1.setText(getData().get(position).get("Name").toString());
        vh.textView2.setText(getData().get(position).get("Position").toString());
        return convertView;
    }

    class viewHolder {
        TextView textView;
        TextView textView1;
        TextView textView2;


    }
    /*添加一个得到数据的方法，方便使用*/
    private ArrayList<HashMap<String, Object>> getData() {
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        /*为动态数组添加数据*/
        for (int i = 0; i < 2; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("Time", "14:00");
            map.put("Name", "课程XXXXXXXX(课时3)");
            map.put("Position", "地址：XX区XX大厦XX楼");
            listItem.add(map);
        }
        return listItem;
    }
}
