package test.wqj.com.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import test.wqj.com.login.R;
import test.wqj.com.login.utils.CircularImage;


public class MyselfActivity extends Fragment {
    private ListView lv1;
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.activity_myself, null) ;
        CircularImage cover_user_photo = (CircularImage) v.findViewById(R.id.cover_user_photo);
        cover_user_photo.setImageResource(R.drawable.touxiang);
        lv1=(ListView)v.findViewById(R.id.my_list1);
        ArrayList<HashMap<String, Object>> listItem = new ArrayList();
        HashMap<String,Object> map1 = new HashMap();
        map1.put("image1", R.drawable.list1);
        map1.put("text", "预约订单");
        map1.put("image2", R.drawable.topo);
        HashMap<String,Object> map2 = new HashMap();
        map2.put("image1", R.drawable.list2);
        map2.put("text", "交易记录");
        map2.put("image2", R.drawable.topo);
        HashMap<String,Object> map3 = new HashMap();
        map3.put("image1", R.drawable.list3);
        map3.put("text", "我的留言");
        map3.put("image2", R.drawable.topo);
        map3.put("image3",R.drawable.ic_my_messager_red);
        HashMap<String,Object> map4 = new HashMap();
        map4.put("image1", R.drawable.list4);
        map4.put("text", "我的作品");
        map4.put("image2", R.drawable.topo);
        HashMap<String,Object> map5 = new HashMap();
        map5.put("image1", R.drawable.list5);
        map5.put("text", "投诉建议");
        map5.put("image2", R.drawable.topo);
        HashMap<String,Object> map6 = new HashMap();
        map6.put("image1", R.drawable.list6);
        map6.put("text", "联系我们");
        map6.put("image2", R.drawable.topo);
        HashMap<String,Object> map7 = new HashMap();
        map7.put("image1", R.drawable.list7);
        map7.put("text", "帮助中心");
        map7.put("image2", R.drawable.topo);
        listItem.add(map1);
        listItem.add(map2);
        listItem.add(map3);
        listItem.add(map4);
        listItem.add(map5);
        listItem.add(map6);
        listItem.add(map7);
        SimpleAdapter smla = new SimpleAdapter(getContext(),listItem,R.layout.listview,new
        String[]{"image1","text","image2","image3"},new int[]{R.id.wqj_limagelist,R.id.wqj_textlist,R.id.wqj_rimagelist,R.id.reddot});
        lv1.setAdapter(smla);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent();
                        intent.setClass(getContext(),SearchActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;



                }
            }
        });
        return v ;
    }


}
