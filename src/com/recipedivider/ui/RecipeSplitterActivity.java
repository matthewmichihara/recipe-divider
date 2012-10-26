package com.recipedivider.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.recipedivider.R;
import com.recipedivider.model.Ingredient;

public class RecipeSplitterActivity extends Activity {

	private static final String TAG = RecipeSplitterActivity.class.getSimpleName();
	private int mOriginalServings = 0;
	private int mDesiredServings = 0;
	private int mCookTime = 0;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_splitter);

		Intent intent = getIntent();
		final ArrayList<Ingredient> ingredients = intent.getParcelableArrayListExtra("ingredients");

		// Log the ingredients that were passed in.
		for (Ingredient ingredient : ingredients) {
			String s = "Name: " + ingredient.getName() + " Quantity: " + ingredient.getQuantity() + " Units: " + ingredient.getUnits();
			Log.i(TAG, s);
		}

		final EditText etOriginalServings = (EditText) findViewById(R.id.et_original_servings);
		final EditText etDesiredServings = (EditText) findViewById(R.id.et_desired_servings);
		final EditText etCookTime = (EditText) findViewById(R.id.et_cook_time);

		final Button btnCalculate = (Button) findViewById(R.id.btn_calculate);
		btnCalculate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				final Intent intent = new Intent(RecipeSplitterActivity.this, RecipeResultsActivity.class);
				try {
					mOriginalServings = Integer.valueOf(etOriginalServings.getText().toString());
				} catch (NumberFormatException e) {
					Log.w(TAG, "Error", e);
				}

				try {
					mDesiredServings = Integer.valueOf(etDesiredServings.getText().toString());
				} catch (NumberFormatException e) {
					Log.w(TAG, "Error", e);
				}

				try {
					mCookTime = Integer.valueOf(etCookTime.getText().toString());
				} catch (NumberFormatException e) {
					Log.w(TAG, "Error", e);
				}

				float ratio = mOriginalServings / mDesiredServings;

				intent.putExtra("ingredients", ingredients);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.activity_recipe_splitter, menu);
		return true;
	}
}
