package test.wqj.com.login.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import test.wqj.com.login.R;

public class PersonActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    //ViewPager控件
    private ViewPager main_viewPager ;
    //RadioGroup控件
    private RadioGroup main_tab_RadioGroup ;
    private RadioButton course , myself , MIDI ;
    //类型为Fragment的动态数组
    private ArrayList<Fragment> fragmentList ;
    private Fragment currentFragment=new Fragment();
    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_person);
        //界面初始函数，用来获取定义的各控件对应的ID
        InitView();
        //ViewPager初始化函数
        InitViewPager();
//        showFragment(course);
    }
    public void InitView()
    {
        main_tab_RadioGroup = (RadioGroup) findViewById(R.id.main_tab_RadioGroup) ;
        course = (RadioButton) findViewById(R.id.course) ;
        myself = (RadioButton) findViewById(R.id.myself) ;
        MIDI = (RadioButton) findViewById(R.id.MIDI) ;
        main_tab_RadioGroup.setOnCheckedChangeListener(this);
    }

    public void InitViewPager()
    {
        main_viewPager = (ViewPager) findViewById(R.id.main_ViewPager);
        fragmentList = new ArrayList() ;
        Fragment course = new CourseActivity() ;
        Fragment myself = new MyselfActivity();
        Fragment MIDI = new MIDIActivity();
        //将各Fragment加入数组中
        fragmentList.add(course);
        fragmentList.add(myself);
        fragmentList.add(MIDI);
        //设置ViewPager的设配器
        main_viewPager.setAdapter(new MyAdapter(getSupportFragmentManager() , fragmentList));
        //当前为第一个页面
        main_viewPager.setCurrentItem(0);
        //ViewPager的页面改变监听器
        main_viewPager.setOnPageChangeListener(new MyListner());
    }
    public class MyAdapter extends FragmentPagerAdapter
    {
        ArrayList<Fragment> list ;
        public MyAdapter(FragmentManager fm , ArrayList<Fragment> list)
        {
            super(fm);
            this.list = list ;
        }
        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }
        @Override
        public int getCount() {
            return list.size();
        }
    }
    public class MyListner implements ViewPager.OnPageChangeListener
    {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {
            //获取当前页面用于改变对应RadioButton的状态
            int current = main_viewPager.getCurrentItem() ;
            switch(current)
            {
                case 0:
                    main_tab_RadioGroup.check(R.id.course);
                    break;
                case 1:
                    main_tab_RadioGroup.check(R.id.myself);
                    break;
                case 2:
                    main_tab_RadioGroup.check(R.id.MIDI);
                    break;
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int CheckedId)
    {
        //获取当前被选中的RadioButton的ID，用于改变ViewPager的当前页
        int current=0;
        switch(CheckedId)
        {
            case R.id.course:
                current = 0 ;
                break ;
            case R.id.myself:
                current = 1 ;
                break;
            case R.id.MIDI:
                current = 2 ;
                break;

        }
        if(main_viewPager.getCurrentItem() != current)
        {
            main_viewPager.setCurrentItem(current);
        }
    }

}
