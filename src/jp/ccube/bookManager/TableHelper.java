package jp.ccube.bookManager;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

public class TableHelper {
	private DBHelper helper;
	private SQLiteDatabase db;
	
	public TableHelper( Context context ) {
		helper = DBHelper.getInstance( context );
		if( helper != null )
			db = helper.getWritableDatabase();
		else
			db = null;
	}
	
	public void close() {
		db.close();
	}

	public void add(AbstractEntity entity) {
		db.insert(entity.getTableName(), null, entity.getValues() );
	}

	public void update(AbstractEntity entity) {
		db.update(entity.getTableName(), entity.getValues(), "_id=?", new String[] { Integer.toString(entity.getId()) });		
	}
	
	public void delete(AbstractEntity entity) {
		db.delete(entity.getTableName(), "_id=?", new String[] {Integer.toString(entity.getId())});
	}
	
	public ArrayList<BookEntity> getAllBook() {
		ArrayList<BookEntity> list = new ArrayList<BookEntity>();
		
		Cursor cur = db.query(BookEntity.TABLE_NAME, 
								new String[] {"_id", "title"}, 
								null, null, null, null, null);
		
		if (cur.moveToFirst()) {
			do {
				BookEntity book = new BookEntity();
				int id = cur.getInt(0);
				String title = cur.getString(1);
				book.setId(id);
				book.setTitle(title);
				list.add(book);
			} while(cur.moveToNext());
		}
		return list;
	}
}
