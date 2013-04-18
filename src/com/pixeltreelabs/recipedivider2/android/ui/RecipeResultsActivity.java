package com.pixeltreelabs.recipedivider2.android.ui;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pixeltreelabs.recipedivider2.android.R;
import com.pixeltreelabs.recipedivider2.android.db.OpenHelper;
import com.pixeltreelabs.recipedivider2.android.db.RecipeProvider;
import com.pixeltreelabs.recipedivider2.android.model.Recipe;

@SuppressLint("NewApi")
public class RecipeResultsActivity extends Activity {
	private static final String TAG = RecipeResultsActivity.class
			.getSimpleName();

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_results);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(R.string.your_recipe);

		final Intent intent = getIntent();
		final Recipe recipe = intent.getParcelableExtra("recipe");
		final boolean showSaveButton = intent.getBooleanExtra("showSaveButton",
				false);
		Log.d(TAG, "recipe: " + recipe);

		final ListView lvIngredients = (ListView) findViewById(R.id.lv_ingredients);
		lvIngredients.setAdapter(new IngredientArrayAdapter(this, recipe
				.getIngredients()));

		final TextView tvRecipeName = (TextView) findViewById(R.id.tv_recipe_name);
		tvRecipeName.setText(recipe.getName());

		final View btnSaveToRecipeBox = findViewById(R.id.btn_save_to_recipe_box);
		if (!showSaveButton) {
			btnSaveToRecipeBox.setVisibility(View.GONE);
		}
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
				intent.putExtra("open_recipe_box_tab", true);
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

		ShareActionProvider shareActionProvider = (ShareActionProvider) menu
				.findItem(R.id.menu_share).getActionProvider();

		final Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		intent.putExtra(Intent.EXTRA_SUBJECT, "Recipe Divider");
		intent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome recipe");

		// Set the default share intent
		shareActionProvider.setShareIntent(intent);

		return true;
	}
}
