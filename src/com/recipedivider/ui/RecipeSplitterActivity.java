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

import com.recipedivider.R;
import com.recipedivider.model.Ingredient;

public class RecipeSplitterActivity extends Activity {

	private static final String TAG = RecipeSplitterActivity.class.getSimpleName();

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_splitter);

		Intent intent = getIntent();
		ArrayList<Ingredient> ingredients = intent.getParcelableArrayListExtra("ingredients");

		for (Ingredient ingredient : ingredients) {
			String s = "Name: " + ingredient.getName() + " Quantity: " + ingredient.getQuantity();
			Log.i(TAG, s);
		}

		final Button btnCalculate = (Button) findViewById(R.id.btn_calculate);
		btnCalculate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				final Intent intent = new Intent(RecipeSplitterActivity.this, RecipeResultsActivity.class);
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