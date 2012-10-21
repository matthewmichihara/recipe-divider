package com.recipedivider.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.recipedivider.R;
import com.recipedivider.model.Ingredient;

public class MainActivity extends SherlockActivity {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final List<Ingredient> ingredients = new ArrayList<Ingredient>();

		final ListView lvIngredients = (ListView) findViewById(R.id.lv_ingredients);
		final IngredientArrayAdapter ingredientAdapter = new IngredientArrayAdapter(this, ingredients);
		lvIngredients.setAdapter(ingredientAdapter);

		final Button btnAddIngredient = (Button) findViewById(R.id.btn_add_ingredient);
		btnAddIngredient.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				ingredients.add(new Ingredient("", 0, "kg"));
				ingredientAdapter.notifyDataSetChanged();
			}
		});

		final Button btnSplitRecipe = (Button) findViewById(R.id.btn_split_recipe);
		btnSplitRecipe.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				final Intent intent = new Intent(MainActivity.this, RecipeSplitterActivity.class);
				startActivity(intent);
			}
		});

		// Add the first ingredient.
		ingredients.add(new Ingredient("", 0, "kg"));
		ingredientAdapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getSupportMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
