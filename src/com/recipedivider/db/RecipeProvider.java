package com.recipedivider.db;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * TODO: This really needs to be fixed up
 * 
 */
public class RecipeProvider extends ContentProvider {
	private OpenHelper mOpenHelper;

	// Used for the UriMacher
	private static final int RECIPES = 10;

	private static final String AUTHORITY = "com.recipedivider.contentprovider";

	private static final String BASE_PATH = "recipes";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/recipes";

	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(AUTHORITY, BASE_PATH, RECIPES);
	}

	@Override
	public boolean onCreate() {
		mOpenHelper = new OpenHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		int uriType = sURIMatcher.match(uri);
		switch (uriType) {
		case RECIPES:
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		Cursor cursor = db.query(OpenHelper.TABLE_RECIPES, projection, selection, selectionArgs, null, null, sortOrder);
		// Make sure that potential listeners are getting notified
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long id = 0;
		switch (uriType) {
		case RECIPES:
			id = db.insert(OpenHelper.TABLE_RECIPES, null, values);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		getContext().getContentResolver().notifyChange(uri, null);
		return Uri.parse(BASE_PATH + "/" + id);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}
}
