package com.recipedivider.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.recipedivider.R;
import com.recipedivider.model.Ingredient;
import com.recipedivider.model.Recipe;

public class RecipeSplitterActivity extends Activity {

	private static final String TAG = RecipeSplitterActivity.class
			.getSimpleName();
	private EditText mEtOriginalServings;
	private EditText mEtDesiredServings;
	private EditText mEtCookTime;
	private String mRecipeName;
	private ArrayList<Ingredient> mIngredients;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_splitter);

		final Intent intent = getIntent();
		mRecipeName = intent.getStringExtra("recipeName");
		mIngredients = intent.getParcelableArrayListExtra("ingredients");

		// Log the passed in extras.
		Log.d(TAG, "recipeName: " + mRecipeName);
		Log.d(TAG, "ingredients: " + mIngredients);

		mEtOriginalServings = (EditText) findViewById(R.id.et_original_servings);
		mEtDesiredServings = (EditText) findViewById(R.id.et_desired_servings);
		mEtCookTime = (EditText) findViewById(R.id.et_cook_time);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case R.id.item_calculate_new_recipe:
			final Intent intent = new Intent(RecipeSplitterActivity.this,
					RecipeResultsActivity.class);
			try {
				final int originalServings = Integer
						.valueOf(mEtOriginalServings.getText().toString());
				final int desiredServings = Integer.valueOf(mEtDesiredServings
						.getText().toString());
				final int cookTime = Integer.valueOf(mEtCookTime.getText()
						.toString());

				final Recipe recipe = new Recipe(mRecipeName, mIngredients,
						originalServings, cookTime);
				final Recipe dividedRecipe = recipe
						.getDividedRecipe(desiredServings);
				intent.putExtra("recipe", dividedRecipe);
				startActivity(intent);
			} catch (NumberFormatException e) {
				Log.w(TAG, "Error", e);
			}

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.activity_recipe_splitter, menu);
		return true;
	}
}
