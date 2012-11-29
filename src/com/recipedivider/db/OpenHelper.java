package com.recipedivider.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {
	public static final String TABLE_RECIPES = "recipes";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_JSON = "json";

	private static final String NAME = "recipedivider.db";
	private static final int VERSION = 1;

	public OpenHelper(Context context) {
		super(context, NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE_RECIPES + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_JSON + " text not null);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
