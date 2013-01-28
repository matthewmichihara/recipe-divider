package com.recipedivider.ui;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.gson.Gson;
import com.recipedivider.R;
import com.recipedivider.db.OpenHelper;
import com.recipedivider.db.RecipeProvider;
import com.recipedivider.model.Recipe;

@SuppressLint("NewApi")
public class RecipeBoxFragment extends Fragment implements
		LoaderCallbacks<Cursor> {

	private ListView mLvRecipes;
	private RecipeCursorAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_recipe_box, container,
				false);

		mLvRecipes = (ListView) v.findViewById(R.id.lv_recipes);
		mLvRecipes.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Cursor cursor = (Cursor) parent.getItemAtPosition(position);
				int jsonColumnIndex = cursor
						.getColumnIndexOrThrow(OpenHelper.COLUMN_JSON);

				String jsonRecipe = cursor.getString(jsonColumnIndex);
				Recipe recipe = new Gson().fromJson(jsonRecipe, Recipe.class);

				Intent intent = new Intent(getActivity(),
						RecipeResultsActivity.class);
				intent.putExtra("recipe", recipe);
				startActivity(intent);
			}
		});

		// Create an empty adapter we will use to display the loaded data.
		mAdapter = new RecipeCursorAdapter(getActivity());
		mLvRecipes.setAdapter(mAdapter);

		getLoaderManager().initLoader(0, null, this);

		return v;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		return new CursorLoader(getActivity(), RecipeProvider.CONTENT_URI,
				null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		mAdapter.swapCursor(arg1);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		mAdapter.swapCursor(null);
	}
}
