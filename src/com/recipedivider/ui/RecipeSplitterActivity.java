package com.recipedivider.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.recipedivider.R;
import com.recipedivider.model.Ingredient;
import com.recipedivider.model.Recipe;

public class RecipeSplitterActivity extends Activity {

	private static final String TAG = RecipeSplitterActivity.class.getSimpleName();

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_splitter);

		final Intent intent = getIntent();
		final String recipeName = intent.getStringExtra("recipeName");
		final ArrayList<Ingredient> ingredients = intent.getParcelableArrayListExtra("ingredients");

		// Log the passed in extras.
		Log.d(TAG, "recipeName: " + recipeName);
		Log.d(TAG, "ingredients: " + ingredients);

		final EditText etOriginalServings = (EditText) findViewById(R.id.et_original_servings);
		final EditText etDesiredServings = (EditText) findViewById(R.id.et_desired_servings);
		final EditText etCookTime = (EditText) findViewById(R.id.et_cook_time);

		final Button btnCalculate = (Button) findViewById(R.id.btn_calculate);
		btnCalculate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				final Intent intent = new Intent(RecipeSplitterActivity.this, RecipeResultsActivity.class);
				try {
					final int originalServings = Integer.valueOf(etOriginalServings.getText().toString());
					final int desiredServings = Integer.valueOf(etDesiredServings.getText().toString());
					final int cookTime = Integer.valueOf(etCookTime.getText().toString());

					final Recipe recipe = new Recipe(recipeName, ingredients, originalServings, cookTime);
					final Recipe dividedRecipe = recipe.getDividedRecipe(desiredServings);
					intent.putExtra("recipe", dividedRecipe);
					startActivity(intent);
				} catch (NumberFormatException e) {
					Log.w(TAG, "Error", e);
				}
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
		getMenuInflater().inflate(R.menu.activity_recipe_splitter, menu);
		return true;
	}
}
