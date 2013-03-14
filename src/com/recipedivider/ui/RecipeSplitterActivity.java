package com.recipedivider.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.recipedivider.R;
import com.recipedivider.model.Ingredient;
import com.recipedivider.model.Recipe;

public class RecipeSplitterActivity extends Activity {

	private static final String TAG = RecipeSplitterActivity.class
			.getSimpleName();
	private EditText mEtOriginalServings;
	private EditText mEtDesiredServings;
	private EditText mEtCookTime;
	private View mCalculateNewRecipeButton;
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
		mCalculateNewRecipeButton = findViewById(R.id.btn_calculate_new_recipe);

		mCalculateNewRecipeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent intent = new Intent(RecipeSplitterActivity.this,
						RecipeResultsActivity.class);
				intent.putExtra("showSaveButton", true);
				try {
					final int originalServings = Integer
							.valueOf(mEtOriginalServings.getText().toString());
					final int desiredServings = Integer
							.valueOf(mEtDesiredServings.getText().toString());
					final int cookTime = Integer.valueOf(mEtCookTime.getText()
							.toString());

					final Recipe recipe = new Recipe(mRecipeName, mIngredients,
							originalServings, cookTime);
					final Recipe dividedRecipe = recipe
							.getDividedRecipe(desiredServings);
					intent.putExtra("recipe", dividedRecipe);
					startActivity(intent);
				} catch (NumberFormatException e) {
					Toast.makeText(RecipeSplitterActivity.this,
							getString(R.string.please_complete_each_field),
							Toast.LENGTH_SHORT).show();
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
}
