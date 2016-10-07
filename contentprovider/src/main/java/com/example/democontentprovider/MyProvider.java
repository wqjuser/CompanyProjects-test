package com.example.democontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

/**
 * 
 * 实现对数据操作的各个方法。这里将用到MyHelper来辅助获得SQLiteDatabase对象，
 * 虽然也可以直接使用Context.OpenOrCreate()方法获得，但不及使用数据库打开辅助类方便。
 *
 */
public class MyProvider extends ContentProvider {
	MyHelper dbHelper;
	private static final UriMatcher sMatcher;
	/**
	 * 这里UriMatcher类型的静态字段是用来匹配传入到ContentProvider中的Uri的类。
	 * 其构造方法传入的匹配码是使用match()方法匹配根路径时返回的值，
	 * 这个匹配码可以为一个大于零的数表示匹配根路径或传入-1，即常量UriMatcher.NO_MATCH表示不匹配根路径。
	 * addURI()方法是用来增加其他URI匹配路径的，第一个参数传入标识ContentProvider的AUTHORITY字符串。
	 * 第二个参数传入需要匹配的路径，这里的#代表匹配任意数字，另外还可以用*来匹配任意文本。
	 * 第三个参数必须传入一个大于零的匹配码，用于match()方法对相匹配的URI返回相对应的匹配码。 
	 */
	static {
		sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sMatcher.addURI(CountryCode.AUTHORITY, "item", CountryCode.ITEM);
		sMatcher.addURI(CountryCode.AUTHORITY, "item/#", CountryCode.ITEM_ID);
	}
	
	
	/**
	 * 每当ContentProvider启动时都会回调onCreate()方法。此方法主要进行一些ContentProvider初始化的工作，
	 * 返回true表示初始化成功，返回false则初始化失败。在这个ContentProvider中，主要是构造了需要操作数据库的辅助类对象。 
	 */
	@Override
	public boolean onCreate() {
		dbHelper = new MyHelper(getContext(), CountryCode.DB_NAME, null,CountryCode.VERSION);
		return true;
	}

    /**
     * delete()和update()方法分别用于数据的删除和修改操作，返回的是所影响数据的数目。
     * 首先利用数据库辅助对象获取一个SQLiteDatabase对象。然后根据传入的Uri用sMatcher进行匹配，
     * 对单个数据或数据集进行删除或修改，以便于在调用SQLiteDatabase对象的删除或修改方法时where语句中使用不同的表达式。
     * 这里通过调用getContext()方法获得调用update()方法的Context对象，再利用这个Context对象来获取一个ContentResolver的对象。
     * notifyChange()方法则用来通知注册在此URI上的观察者（observer）数据发生了改变。最后返回删除或修改数据的行数。 	
     */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;
		switch (sMatcher.match(uri)) {
		case CountryCode.ITEM:
			count = db.delete(CountryCode.TB_NAME, selection, selectionArgs);
			break;
		case CountryCode.ITEM_ID:
			String id = uri.getPathSegments().get(1);
			count = db.delete(CountryCode.TB_NAME, CountryCode.ID
					+ "="
					+ id
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count; 
	}

	/**
	 * getTyper()是用来返回数据的MIME类型的方法。
	 * 使用sMatcher对URI进行匹配，并返回相应的MIME类型字符串，
	 * 若无法匹配传入的URI，抛出IllegalArgumentException异常。 
	 */
	@Override
	public String getType(Uri uri) {
		switch (sMatcher.match(uri)) {
		case CountryCode.ITEM:
			return CountryCode.CONTENT_TYPE;
		case CountryCode.ITEM_ID:
			return CountryCode.CONTENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	/**
	 * insert()方法用来插入数据，最后返回新插入数据的URI。
	 * 在此方法的实现中，只接受数据集的URI，即指向表的URI。
	 * 然后利用数据库辅助对象获得的SQLiteDatabase对象，
	 * 调用insert()方法向指定表中插入数据。最后通知观察者数据发生变化，
	 * 返回插入数据的URI。 
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long rowId;
		if (sMatcher.match(uri) != CountryCode.ITEM) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		rowId = db.insert(CountryCode.TB_NAME, CountryCode.ID, values);
		
		if (rowId > 0) {
			Uri noteUri = ContentUris.withAppendedId(CountryCode.CONTENT_URI,rowId);
			getContext().getContentResolver().notifyChange(noteUri, null);
			return noteUri;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}
	
	/**
	 * query()是对数据进行查询的方法，最终将查询的结果包装入一个Cursor对象并返回。
	 * 其实现首先还是通过数据库辅助对象获取一个SQLiteDatabase对象，然后使用sMatcher
	 * 对传入URI进行匹配，并分别对单数据和多数据的URI构造不同的where语句表达式并查询。
	 * setNotificationUri()方法是用来为Cursor对象注册一个观察数据变化的URI。 
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c;
		switch (sMatcher.match(uri)) {
		case CountryCode.ITEM:
			c = db.query(CountryCode.TB_NAME, projection, selection,selectionArgs, null, null, sortOrder);
			break;
		case CountryCode.ITEM_ID:
			String id = uri.getPathSegments().get(1);
			c = db.query(CountryCode.TB_NAME, projection, CountryCode.ID
					+ "="
					+ id
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs, null, null, sortOrder);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;
		switch (sMatcher.match(uri)) {
		case CountryCode.ITEM:
			count = db.update(CountryCode.TB_NAME, values, selection,selectionArgs);
			break;
		case CountryCode.ITEM_ID:
			String id = uri.getPathSegments().get(1);
			count = db.update(CountryCode.TB_NAME, values, CountryCode.ID
					+ "="
					+ id
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;  
	}

}
