package jp.ccube.bookManager;

import android.content.ContentValues;

public abstract class AbstractEntity {
	public abstract String getTableName();
	public abstract ContentValues getValues();
	
	private int id;
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
}
