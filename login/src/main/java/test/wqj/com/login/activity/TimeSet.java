package test.wqj.com.login.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.wqj.com.login.R;

public class TimeSet extends AppCompatActivity {
    private GridView gridView;
    private List<Integer> booleans;
    private ArrayList<String> list;
    private SeriesFragmentDataAdapter seriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_time_set);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int SCREEN_HEIGHT = dm.heightPixels;
        gridView = (GridView) findViewById(R.id.gridview);
        list = new ArrayList<>();
        booleans = new ArrayList<>();
        seriesAdapter = new SeriesFragmentDataAdapter(this, list);
        //添加表头
        addHeader();
        //添加第一行数据
        addData();
        gridView.setAdapter(seriesAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
//                Toast.makeText(TimeSet.this,list.size(),Toast.LENGTH_SHORT).show();
                seriesAdapter.clearSelection(position);
                seriesAdapter.notifyDataSetChanged();
            }
        });
    }
    public void addHeader() {
        String items[] = {"", "周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        for (String strText : items) {
            booleans.add(0);
            list.add(strText);
        }
        seriesAdapter.notifyDataSetChanged(); //更新数据
    }
    public void addData() {
        String titles[] = {"9:00  10:00", "10:00  11:00", "11:00  12:00", "12:00  13:00", "13:00  14:00",
                "14:00  15:00", "15:00  16:00", "16:00  17:00", "17:00  18:00", "18:00  19:00",
                "19:00  20:00", "20:00  21:00"};
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0) {
                    list.add(titles[i]);

                } else {
                    list.add("忙碌");
                }
                if (j == 0) {
                    booleans.add(3);
                } else {
                    booleans.add(2);
                }
            }
        }
        seriesAdapter.notifyDataSetChanged(); //更新数据
    }

    class SeriesFragmentDataAdapter extends BaseAdapter {
        final int itemLength = 104;
        private LayoutInflater inflater;
        private List<String> numberList = new ArrayList<String>();
        private int selectedPosition = 0;
        private int[] clickedList = new int[itemLength];//这个数组用来存放item的点击状态

        public SeriesFragmentDataAdapter(Context context, ArrayList<String> numberList) {
            inflater = LayoutInflater.from(context);
            this.numberList = numberList;
            for (int i = 0; i < numberList.size(); i++) {
                clickedList[i] = 0;      //初始化item点击状态的数组
            }
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return numberList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return numberList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public void clearSelection(int position) {

            selectedPosition = position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder;
            convertView = View.inflate(TimeSet.this, R.layout.griditem, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.textview = (TextView) convertView.findViewById(R.id.item_tv);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.grid_li);
            holder = (ViewHolder) convertView.getTag();
            holder.textview.setText(list.get(position));
            if (booleans.get(position) == 0) {
                //第一行颜色
                holder.textview.setTextColor(Color.parseColor("#000000"));
                holder.textview.setBackgroundColor(Color.parseColor("#FFFFFF"));
                holder.linearLayout.setBackgroundColor(Color.WHITE);

            }
            //第一列的颜色
            else if (booleans.get(position) == 3) {
                holder.textview.setTextColor(Color.parseColor("#C7C7C7"));
                holder.linearLayout.setBackgroundResource(R.drawable.firstback);
                holder.linearLayout.setBackgroundColor(Color.WHITE);
            } else {
                //表内颜色
                holder.textview.setTextColor(Color.parseColor("#FFFFFF"));
                holder.linearLayout.setBackgroundResource(R.drawable.firstback);
                holder.textview.setBackgroundColor(Color.parseColor("#99C8CC"));
                holder.linearLayout.setBackgroundColor(Color.parseColor("#99C8CC"));
            }
            if (booleans.get(position) != 3 && booleans.get(position) != 0) {
                if (selectedPosition == position) {
                    if (clickedList[position] == 0) {
                        holder.textview.setText("空闲");
                        holder.textview.setTextColor(Color.WHITE);
                        holder.linearLayout.setBackgroundResource(R.drawable.firstback);
                        holder.textview.setBackgroundColor(Color.parseColor("#B7D299"));
                        clickedList[position] = 1;
                    } else {
                        convertView.setBackgroundColor(Color.TRANSPARENT);
                        clickedList[position] = 0;
                    }
                } else {
                    if (clickedList[position] == 1) {
                        holder.textview.setText("空闲");
                        holder.textview.setTextColor(Color.WHITE);
                        holder.linearLayout.setBackgroundResource(R.drawable.firstback);
                        holder.textview.setBackgroundColor(Color.parseColor("#B7D299"));
                    } else if (clickedList[position] == 0) {
                        convertView.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
            return convertView;
        }

        public class ViewHolder {
            public TextView textview;
            public LinearLayout linearLayout;
        }
    }

}
