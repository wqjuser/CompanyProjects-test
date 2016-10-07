package test.wqj.com.login.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.wqj.com.login.R;
import test.wqj.com.login.adapter.FragmentAdapter;
import test.wqj.com.login.bean.ConmmonVariable;
import test.wqj.com.login.utils.AutoPlayInfo;
import test.wqj.com.login.utils.AutoPlayingViewPager;
import test.wqj.com.login.utils.CenterTextView;
import test.wqj.com.login.utils.MyListView;

public class MIDIActivity extends Fragment {
    private GridView gridView;
    //    private RadioGroup radioGroup;
//    private RadioButton radioButton;
    private ScrollView scroll;
    private View gridItem;
    private TabLayout mTabLayout;
    private SimpleAdapter sim_adapter;
    private TextView re_price;
    private AdapterForListView adapter;
    private AdapterForListView1 adapter1;
    private MyListView albumname;
    private MyListView nameofyq;
    private List<Map<String, Object>> data_list;
    private int[] icon = {R.drawable.yq, R.drawable.yq,
            R.drawable.yq, R.drawable.yq, R.drawable.yq, R.drawable.yq, R.drawable.yq, R.drawable.yq};
    private AutoPlayingViewPager mAutoPlayingViewPager;
    private int[] imageId = new int[]{
            R.drawable.cd, R.drawable.gq, R.drawable.jzg, R.drawable.xtq};
    private List<AutoPlayInfo> mAutoPlayInfoList;

    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gridItem = View.inflate(getContext(), R.layout.yq_griditem, null);
        View v = inflater.inflate(R.layout.activity_midi, null);
        gridView = (GridView) v.findViewById(R.id.yq_f_grid);
        scroll = (ScrollView) v.findViewById(R.id.yq_scoll);
        scroll.smoothScrollTo(0, 0);
        mTabLayout = (TabLayout) v.findViewById(R.id.yq_tabs1);
        albumname = (MyListView) v.findViewById(R.id.yq_zj_scr);
        nameofyq = (MyListView) v.findViewById(R.id.yq_name_list);
        adapter = new AdapterForListView(getContext());
        adapter1 = new AdapterForListView1(getContext());
        albumname.setAdapter(adapter);
        nameofyq.setAdapter(adapter1);
        TextView re_pr = (TextView) gridItem.findViewById(R.id.grid_re_price);
        re_pr.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        getData();
        //新建适配器
        String[] from = {"image", "nameOfYq", "price", "re_price"};
        int[] to = {R.id.grid_imv, R.id.grid_name_yq, R.id.grid_price, R.id.grid_re_price};
        sim_adapter = new SimpleAdapter(getContext(), data_list, R.layout.yq_griditem, from, to);
        //配置适配器
        gridView.setAdapter(sim_adapter);
        mAutoPlayingViewPager = (AutoPlayingViewPager) v.findViewById(R.id.auto_play_viewpager);
        //使用异步加载模拟本地请求
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
        initViewPager();
        return v;
    }

    private void initViewPager() {

        List<String> titles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                titles.add("全部");
            } else {
                titles.add("分类" + i);
            }
        }
        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new ListFragment());
        }
        FragmentAdapter mFragmentAdapteradapter = new FragmentAdapter(getFragmentManager(), fragments, titles);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);
        mTabLayout.getTabAt(ConmmonVariable.comm).select();
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ConmmonVariable.comm=tab.getPosition();
                Intent intent= new Intent();
                intent.setClass(getContext(),MusicalInstrument.class);
                startActivity(intent);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Intent intent= new Intent();
                intent.setClass(getContext(),MusicalInstrument.class);
                startActivity(intent);
            }
        });
    }

    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
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

    public class AdapterForListView extends BaseAdapter {

        private LayoutInflater inflater;

        public AdapterForListView(Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder h = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.album_listitem, null);
                h = new ViewHolder();
                h.imageView = (ImageView) convertView.findViewById(R.id.yq_list_img);
                convertView.setTag(h);
            } else {
                h = (ViewHolder) convertView.getTag();
            }
            for (int i = 0; i < 4; i++) {
                h.imageView.setImageResource(imageId[position]);
            }


            return convertView;
        }

        class ViewHolder {

            ImageView imageView;

        }
    }

    public class AdapterForListView1 extends BaseAdapter {

        private LayoutInflater inflater;

        public AdapterForListView1(Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder h = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.yq_name_listitem, null);
                h = new ViewHolder();
                h.yq_p1 = (ImageView) convertView.findViewById(R.id.yq_p1);
                h.yq_p2 = (ImageView) convertView.findViewById(R.id.yq_p2);
                h.yq_p3 = (ImageView) convertView.findViewById(R.id.yq_p3);
                h.yq_p4 = (ImageView) convertView.findViewById(R.id.yq_p4);
                h.yq_p5 = (ImageView) convertView.findViewById(R.id.yq_p5);
                h.moneysign = (TextView) convertView.findViewById(R.id.yq_money_sign);
                h.money = (TextView) convertView.findViewById(R.id.yq_money);
                h.nameofyq = (TextView) convertView.findViewById(R.id.yq_name);
                h.ctv = (CenterTextView) convertView.findViewById(R.id.nameofyq_center);
                convertView.setTag(h);
            } else {
                h = (ViewHolder) convertView.getTag();
            }
            for (int i = 0; i < 4; i++) {
                h.yq_p1.setImageResource(R.drawable.p1);
                h.yq_p2.setImageResource(R.drawable.p1);
                h.yq_p3.setImageResource(R.drawable.p1);
                h.yq_p4.setImageResource(R.drawable.p1);
                h.yq_p5.setImageResource(R.drawable.p1);
                h.ctv.setText("音质优美 配件齐全 手工实木 音质优美 配件齐全 手工实木 音质优美 配件齐全 手工实木");
                h.nameofyq.setText("乐器名称乐器名称");
                h.moneysign.setText("¥");
                h.money.setText("318.00");
            }
            return convertView;
        }

        class ViewHolder {
            CenterTextView ctv;
            ImageView yq_p1;
            ImageView yq_p2;
            ImageView yq_p3;
            ImageView yq_p4;
            ImageView yq_p5;
            TextView nameofyq;
            TextView moneysign;
            TextView money;

        }
    }


    /*添加一个得到数据的方法，方便使用*/
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
