package test.wqj.com.login.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.wqj.com.login.R;
import test.wqj.com.login.adapter.FragmentAdapter;
import test.wqj.com.login.bean.ConmmonVariable;

public class MusicalInstrument extends AppCompatActivity {
    private GridView gridView;
    private SimpleAdapter sim_adapter;
    private TabLayout mTabLayout;
    private MaterialRefreshLayout mRefreshLayout;
    private List<Map<String, Object>> data_list;
    /**
     * 在上拉刷新的时候，判断，是否处于上拉刷新，如果是的话，就禁止在一次刷新，保障在数据加载完成之前
     * 避免重复和多次加载
     */
    private boolean isLoadMore = true;
    private int[] icon = {R.drawable.yq, R.drawable.yq,
            R.drawable.yq, R.drawable.yq, R.drawable.yq, R.drawable.yq, R.drawable.yq, R.drawable.yq};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_musical_instrument);
        gridView = (GridView) findViewById(R.id.yq_grid1);
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        getData();
        //新建适配器
        String[] from = {"image", "nameOfYq", "price", "re_price"};
        int[] to = {R.id.grid_imv, R.id.grid_name_yq, R.id.grid_price, R.id.grid_re_price};
        sim_adapter = new SimpleAdapter(MusicalInstrument.this, data_list, R.layout.yq_griditem, from, to);
        //配置适配器
        gridView.setAdapter(sim_adapter);
        mRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.mu_refresh);
        /**
         * 设置是否上拉加载更多，默认是false，要手动改为true，要不然不会出现上拉加载
         */
        mRefreshLayout.setLoadMore(isLoadMore);
        ListenerMR();
        initViewPager();
    }

    private void ListenerMR() {
        mRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
                                                      /**
                                                       * 刷新的方法，我这里演示的是下拉刷新，因为没有数据，我这里也就只是toast一下
                                                       * 如果想要实现你们自己要的结果，就会在定义一个方法，获取最新数据，或者是在次
                                                       * 在这里调用之前获取数据的方法，以达到刷新数据的功能
                                                       * @param materialRefreshLayout
                                                       */
                                                      @Override
                                                      public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                                                          //一般加载数据都是在子线程中，这里我用到了handler
                                                          new Handler().postDelayed(new Runnable() {
                                                              @Override
                                                              public void run() {
                                                                  Toast.makeText(MusicalInstrument.this, "已经没有更多数据了", Toast.LENGTH_SHORT).show();
                                                                  /**
                                                                   * 刷新完成后调用此方法，要不然刷新效果不消失
                                                                   */
                                                                  mRefreshLayout.finishRefresh();
                                                              }
                                                          }, 3000);
                                                      }

                                                      /**
                                                       * 上拉加载更多的方法，在这里我只是简单的模拟了加载四条数据
                                                       * 真正用的时候，就会去定义方法，获取数据，一般都是分页，在数据端获取的时候
                                                       * 把页数去增加一，然后在去服务端去获取数据
                                                       * @param materialRefreshLayout
                                                       */
                                                      @Override
                                                      public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                                                          new Handler().postDelayed(new Runnable() {
                                                                                        @Override
                                                                                        public void run() {
                                                                                            for (int a = 0; a < 4; a++) {
                                                                                                Map<String, Object> map = new HashMap<String, Object>();
                                                                                                map.put("image", icon[a]);
                                                                                                map.put("nameOfYq", "乐器名称乐器名称");
                                                                                                map.put("price", "¥ 15.00");
                                                                                                map.put("re_price", "30.00");
                                                                                                data_list.add(map);
                                                                                            }
                                                                                            sim_adapter.notifyDataSetChanged();
                                                                                            Toast.makeText(MusicalInstrument.this, "加载完成", Toast.LENGTH_SHORT).show();
//                        isLoadMore = false;
//                        for (int i = 0; i <= 3; i++) {
//                            Map<String, Object> map = new HashMap<String, Object>();
//                            map.put("image", icon[i]);
//                            map.put("nameOfYq", "乐器名称乐器名称");
//                            map.put("price", "¥ 15.00");
//                            map.put("re_price", "30.00");
//                            data_list.add(map);
//                        }
                                                                                            //通知刷新
//                        sim_adapter.addLists(mAdapter.getLists().size(), mList);
                                                                                            //mRecyclerView.scrollToPosition(mAdapter.getLists().size());
                                                                                            /**
                                                                                             * 完成加载数据后，调用此方法，要不然刷新的效果不会消失
                                                                                             */
                                                                                            mRefreshLayout.finishRefreshLoadMore();
                                                                                        }
                                                                                    }

                                                                  , 3000);
                                                      }
                                                  }

        );
    }

    private void initViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.yq_tabs);
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
        FragmentAdapter mFragmentAdapteradapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);
        mTabLayout.getTabAt(ConmmonVariable.comm).select();
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                Toast.makeText(MusicalInstrument.this, tab.getPosition() + ":" + tab.getText(), Toast.LENGTH_SHORT).show();
                changeGridView(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                sim_adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                sim_adapter.notifyDataSetChanged();
            }
        });
    }


    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        if (ConmmonVariable.comm == 0) {
            for (int i = 0; i < icon.length; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("image", icon[1]);
                map.put("nameOfYq", "乐器名称乐器名称");
                map.put("price", "¥ 15.00");
                map.put("re_price", "30.00");
                data_list.add(map);
            }
        } else {
            for (int i = 0; i < ConmmonVariable.comm; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("image", icon[1]);
                map.put("nameOfYq", "乐器名称乐器名称");
                map.put("price", "¥ 15.00");
                map.put("re_price", "30.00");
                data_list.add(map);
            }
        }
        return data_list;
    }

    public void changeGridView(int i) {
        switch (i) {
            case 0:
                NotifyData(icon.length);
                sim_adapter.notifyDataSetChanged();
                break;
            case 1:
                NotifyData(1);
                sim_adapter.notifyDataSetChanged();
                break;
            case 2:
                NotifyData(2);
                sim_adapter.notifyDataSetChanged();
                break;
            case 3:
                NotifyData(3);
                sim_adapter.notifyDataSetChanged();
                break;
            case 4:
                NotifyData(4);
                sim_adapter.notifyDataSetChanged();
                break;
            case 5:
                NotifyData(5);
                sim_adapter.notifyDataSetChanged();
                break;
            case 6:
                NotifyData(6);
                sim_adapter.notifyDataSetChanged();
                break;
            case 7:
                NotifyData(7);
                sim_adapter.notifyDataSetChanged();
                break;
            case 8:
                NotifyData(8);
                sim_adapter.notifyDataSetChanged();
                break;
            case 9:
                NotifyData(9);
                sim_adapter.notifyDataSetChanged();
                break;
        }

    }

    public void NotifyData(int i) {
        data_list.clear();
        for (int a = 0; a < i; a++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[1]);
            map.put("nameOfYq", "乐器名称乐器名称");
            map.put("price", "¥ 15.00");
            map.put("re_price", "30.00");
            data_list.add(map);
        }
    }
}
