package com.example.democontentprovider;

import android.net.Uri;

/**
 * 装入所有与数据库操作有关的静态字段，以便于打包成JAR文件供其他应用程序调用。 
 */
public class CountryCode {
	/**数据库  */
	public static final String  DB_NAME= "code.db";  
	/**表的名称  */
	public static final String TB_NAME = "countrycode";
	/**数据库的版本号 */
	public static final int VERSION = 4;
	

	/**表中的各个列的列名 */
	public static final String ID = "_id";    
	public static final String COUNTRY = "country";    
	public static final String CODE = "code";    
	 
	/**定义了标识ContentProvider的字符串    */
	public static final String AUTHORITY = "com.example.andriod.provider.countrycode";
	/**ITEM和ITEM_ID分别用于UriMatcher（资源标识符匹配器）中对路径item和item/id的匹配号码*/
	public static final int ITEM = 1;    
	public static final int ITEM_ID = 2;    
	
	/**
	 * CONTENT_TYPE和CONTENT_ITEM_TYPE定义了数据的MIME类型。
	 * 需要注意的是，单一数据的MIME类型字符串应该以vnd.android.cursor.item/开头，
	 * 数据集的MIME类型字符串则应该以vnd.android.cursor.dir/开头。
	 */
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.example.android.countrycode";    
	public static final String CONTENT_ITEM_TYPE =  "vnd.android.cursor.item/vnd.example.android.countrycode";    
	
	/**CONTENT_URI定义了查询当前表数据的content://样式URI。 */
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/item");    
}
