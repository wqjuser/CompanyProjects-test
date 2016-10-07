package com.example.democontentresolver;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.example.contentprovider.R;

public class MainActivity extends Activity {

	private Button insert,query,delete;
	private EditText country,code;
	private ListView listView;
	private Cursor cursor;
	
	public static final String AUTHORITY = "com.example.andriod.provider.countrycode";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/item");
	public static final String ID = "_id";    
	public static final String COUNTRY = "country";    
	public static final String CODE = "code";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        insert=(Button)findViewById(R.id.insert);
        query=(Button)findViewById(R.id.query);
        country=(EditText)findViewById(R.id.editCountry);
        code=(EditText)findViewById(R.id.editCode);
        listView=(ListView)findViewById(R.id.my_list);
        delete=(Button)findViewById(R.id.delete);
        
        cursor=this.getContentResolver().query(CONTENT_URI, new String[]{ID,COUNTRY,CODE}, null, null, null);
        
        listView.setAdapter(new MyAdapter());
        
        new Thread(){
        	@Override
        	public void run() {
        		listView.setAdapter(new MyAdapter());
        		super.run();
        	}
        };
        
        insert.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContentValues values=new ContentValues();
				values.put(COUNTRY, country.getEditableText().toString());
				values.put(CODE, code.getEditableText().toString());
				
			    ContentResolver resolver=MainActivity.this.getContentResolver();
			    resolver.insert(CONTENT_URI, values);
				
			}
		});
        
        delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContentResolver resolver=MainActivity.this.getContentResolver();
				cursor.moveToFirst();
				
				resolver.delete(Uri.withAppendedPath(CONTENT_URI, String.valueOf(cursor.getInt(0))), null, null);
				
			}
		});
        
        query.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cursor=MainActivity.this.getContentResolver().query(CONTENT_URI, new String[]{ID,COUNTRY,CODE}, null, null, null);
				((MyAdapter)listView.getAdapter()).notifyDataSetChanged();
				
			}
		});
        

    }
    
    public class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return cursor.getCount();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			cursor.moveToFirst();
			cursor.moveToPosition(position);
			TextView text;
			if(convertView != null){
				text = (TextView)convertView;
			}else{	
				convertView = new TextView(MainActivity.this);  
				text=(TextView)convertView;
			}
			text.setText("国家:"+cursor.getString(0)+" 国码:"+cursor.getString(1));
			text.setTextColor(0xFFFFFFFF);
		    return convertView;
		}
    	
    }

}
