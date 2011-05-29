package jp.ccube.bookManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


public class DBHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "jp.ccube.bookManager.db";
	private static final int DB_VERSION = 1;
	private int writableDatabaseCount = 0;

	private static DBHelper instance = null;

	synchronized static
	public DBHelper getInstance(Context context) {
		if (instance == null) {
			instance = new DBHelper(context.getApplicationContext());
		}
		
		return instance;
	}
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"CREATE TABLE IF NOT EXISTS Books ( _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , title TEXT )"
				);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	@Override
	synchronized public SQLiteDatabase getWritableDatabase()
	{
		SQLiteDatabase db = super.getWritableDatabase();
		if ( db != null )
		{
			++writableDatabaseCount;
		}
			
		return db;
	}

	synchronized public void closeWritableDatabase( SQLiteDatabase database )
	{
		if ( writableDatabaseCount > 0 && database != null )
		{
			--writableDatabaseCount;
			if ( writableDatabaseCount == 0 )
			{
				database.close();
			}
		}
	}
}
