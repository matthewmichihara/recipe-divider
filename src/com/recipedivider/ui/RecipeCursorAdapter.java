package com.recipedivider.ui;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.recipedivider.R;
import com.recipedivider.db.OpenHelper;
import com.recipedivider.model.Recipe;

class RecipeCursorAdapter extends CursorAdapter {
	private final Gson mGson;

	public RecipeCursorAdapter(Context context) {
		super(context, null, 0);

		mGson = new Gson();
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		return layoutInflater.inflate(R.layout.list_item_recipe, null);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		int jsonColumnIndex = cursor.getColumnIndexOrThrow(OpenHelper.COLUMN_JSON);

		String jsonRecipe = cursor.getString(jsonColumnIndex);
		Recipe recipe = mGson.fromJson(jsonRecipe, Recipe.class);

		TextView label = (TextView) view.findViewById(R.id.tv_recipe);
		label.setText(recipe.getName());
	}
}