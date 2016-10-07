package com.example.democontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyHelper extends SQLiteOpenHelper{


	public MyHelper(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.beginTransaction();
		
		db.execSQL(CREATE_TABLE);
		
//		ContentValues values = new ContentValues();
//		values.put(CountryCode.COUNTRY, "中国");
//		values.put(CountryCode.CODE, 86);
//		db.insertOrThrow(CountryCode.TB_NAME, CountryCode.ID, values);
		db.setTransactionSuccessful();
		db.endTransaction();
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.beginTransaction();
		Log.e("onupgrade", "onupgrade");
		db.execSQL(DROP_TABLE);
		db.execSQL(CREATE_TABLE);
		
		db.setTransactionSuccessful();
		db.endTransaction();
		
		onCreate(db);
		
	}
	private static final String[] TABLE_COLUMNS = {CountryCode.ID, CountryCode.COUNTRY, CountryCode.CODE};
	
	private static final String CREATE_TABLE = 
			"CREATE TABLE IF NOT EXISTS " +  CountryCode.TB_NAME + " ("
			+ TABLE_COLUMNS[0] + " INTEGER PRIMARY KEY,"	//主键
			+ TABLE_COLUMNS[1] + " VARCHAR(20),"	//国家
			+ TABLE_COLUMNS[2] + " VARCHAR(20)"	//国码
			+ ")";
	private static final String DROP_TABLE=
			"DROP TABLE IF EXISTS " + CountryCode.TB_NAME;

}
