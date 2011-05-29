package jp.ccube.bookManager;

import android.content.ContentValues;

public class BookEntity extends AbstractEntity {
	public final static String TABLE_NAME = "Books";
	
	private String title;
	private String auther;
	private int volume;
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String getTableName() {
		return this.TABLE_NAME;
	}

	@Override
	public ContentValues getValues() {
		ContentValues val = new ContentValues();
		val.put( "title", this.title);
		return val;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

	public String getAuther() {
		return auther;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getVolume() {
		return volume;
	}
}
