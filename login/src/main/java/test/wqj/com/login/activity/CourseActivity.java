package test.wqj.com.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import test.wqj.com.login.R;
import test.wqj.com.login.utils.AutoPlayInfo;
import test.wqj.com.login.utils.AutoPlayingViewPager;

public class CourseActivity extends Fragment {
    private ListView lv;
    private AutoPlayingViewPager mAutoPlayingViewPager;
    private TextView course_name;
    private TextView leavel;
    private TextView teacher;
    private TextView place;
    private  String kcname;
    /**
     * 模拟本地请求获取的图片Id
     */
    private int[] imageId = new int[]{
            R.drawable.cd, R.drawable.gq, R.drawable.jzg, R.drawable.xtq};

    private String[] imageTitle = new String[]{
            "课程XXXXXXXX", "课程XXXXXXXX", "课程XXXXXXXX", "课程XXXXXXXX"};
    private List<AutoPlayInfo> mAutoPlayInfoList;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_course, null);
        mAutoPlayingViewPager = (AutoPlayingViewPager) v.findViewById(R.id.auto_play_viewpager);
        course_name=(TextView)v.findViewById(R.id.kc_tv1) ;
        leavel=(TextView)v.findViewById(R.id.kc_tv2) ;
        teacher=(TextView)v.findViewById(R.id.kc_tv3) ;
        place=(TextView)v.findViewById(R.id.kc_tv4) ;
        lv = (ListView) v.findViewById(R.id.kc_lv);
        lv.setAdapter(new CourseActivity.MyAdapter(getContext()));

       //使用异步加载模拟本地请求
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(v.getContext(), "第"+position+"项被点击了", Toast.LENGTH_SHORT).show();
//                kcname=course_name.getText().toString();
                Toast.makeText(getContext(), kcname, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
//                intent.putExtra("kc_name",course_name.getText().toString());
//                intent.putExtra("lea",leavel.getText().toString());
//                intent.putExtra("tea",teacher.getText().toString());
//                intent.putExtra("place",place.getText().toString());
                intent.setClass(getContext(),CourseDetails.class);
                startActivity(intent);
                Toast.makeText(getContext(), "ceshi", Toast.LENGTH_SHORT).show();

            }
        });
        return v;
    }


    /*添加一个得到数据的方法，方便使用*/
    private ArrayList<HashMap<String, Object>> getData() {
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        /*为动态数组添加数据*/
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ImageView", R.drawable.kc_2);
                map.put("Name", "课程XXXXXXXX");
                map.put("Content", "难度等级：A");
                map.put("Teacher", "老师：XXX");
                map.put("Position", "XX区XX大厦XX楼");
                map.put("Status", "已开通");
                listItem.add(map);
            } else {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ImageView", R.drawable.kc_2);
                map.put("Name", "课程XXXXXXXX");
                map.put("Content", "难度等级：A");
                map.put("Teacher", "老师：XXX");
                map.put("Position", "XX区XX大厦XX楼");
                map.put("Status", "未开通");
                listItem.add(map);
            }
        }
        return listItem;
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater; //得到一个LayoutInfalter对象用来导入布局
        public MyAdapter(Context context) {
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
            viewHolder vh = new viewHolder();
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.kclistview, null);
                vh.textView = (TextView) convertView.findViewById(R.id.kc_tv1);
                vh.textView1 = (TextView) convertView.findViewById(R.id.kc_tv2);
                vh.textView2 = (TextView) convertView.findViewById(R.id.kc_tv3);
                vh.textView3 = (TextView) convertView.findViewById(R.id.kc_tv4);
                vh.imageView = (ImageView) convertView.findViewById(R.id.kc_imb1);
                vh.button = (Button) convertView.findViewById(R.id.kc_button);
                convertView.setTag(vh);
            }
            vh = (viewHolder) convertView.getTag();
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
            vh.imageView.setImageResource(R.drawable.kc_2);
            vh.textView.setText(getData().get(position).get("Name").toString());
            vh.textView1.setText(getData().get(position).get("Content").toString());
            vh.textView2.setText(getData().get(position).get("Teacher").toString());
            vh.textView3.setText(getData().get(position).get("Position").toString());
            vh.button.setText(getData().get(position).get("Status").toString());
            return convertView;
        }

        class viewHolder {
            ImageView imageView;
            TextView textView;
            TextView textView1;
            TextView textView2;
            TextView textView3;
            Button button;
        }
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            //模拟网络请求获取数据
            try {
                Thread.sleep(10);//模拟休眠0.01秒
                mAutoPlayInfoList = changeAutoPlayInfoList();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //数据加载后更新UI
            mAutoPlayingViewPager.initialize(mAutoPlayInfoList).build();
            mAutoPlayingViewPager.startPlaying();
        }
    }

    /**
     * 将数据转换为AutoPlayInfo形式
     */
    private List<AutoPlayInfo> changeAutoPlayInfoList() {
        List<AutoPlayInfo> autoPlayInfoList = new ArrayList<AutoPlayInfo>();
        for (int i = 0; i < imageId.length; i++) {
            AutoPlayInfo autoPlayInfo = new AutoPlayInfo();
            autoPlayInfo.setImageId(imageId[i]);
            autoPlayInfo.setTitle(imageTitle[i]);
            autoPlayInfoList.add(autoPlayInfo);
        }
        return autoPlayInfoList;
    }

    @Override
    public void onResume() {
        //没有数据时不执行startPlaying,避免执行几次导致轮播混乱
        if (mAutoPlayInfoList != null && !mAutoPlayInfoList.isEmpty()) {
            mAutoPlayingViewPager.startPlaying();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        mAutoPlayingViewPager.stopPlaying();
        super.onPause();
    }


}
