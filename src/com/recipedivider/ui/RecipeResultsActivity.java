package com.recipedivider.ui;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.recipedivider.R;
import com.recipedivider.db.OpenHelper;
import com.recipedivider.db.RecipeProvider;
import com.recipedivider.model.Recipe;

public class RecipeResultsActivity extends Activity {
	private static final String TAG = RecipeResultsActivity.class
			.getSimpleName();

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_results);

		final Intent intent = getIntent();
		final Recipe recipe = intent.getParcelableExtra("recipe");
		Log.d(TAG, "recipe: " + recipe);

		final ListView lvIngredients = (ListView) findViewById(R.id.lv_ingredients);
		lvIngredients.setAdapter(new IngredientArrayAdapter(this, recipe
				.getIngredients()));

		final Button btnShare = (Button) findViewById(R.id.btn_share);
		btnShare.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				final Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
				intent.putExtra(Intent.EXTRA_SUBJECT, "Hello World");
				intent.putExtra(Intent.EXTRA_TEXT,
						"Check out this awesome recipe");
				startActivity(Intent.createChooser(intent, "Share your recipe"));
			}
		});

		final Button btnSaveToRecipeBox = (Button) findViewById(R.id.btn_save_to_recipe_box);
		btnSaveToRecipeBox.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				Gson gson = new Gson();
				String jsonRecipe = gson.toJson(recipe);

				ContentValues cv = new ContentValues();
				cv.put(OpenHelper.COLUMN_JSON, jsonRecipe);

				getContentResolver().insert(RecipeProvider.CONTENT_URI, cv);

				Intent intent = new Intent(RecipeResultsActivity.this,
						MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.activity_recipe_results, menu);
		return true;
	}
}
