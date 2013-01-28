package com.recipedivider.ui;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.recipedivider.R;
import com.recipedivider.model.Ingredient;

@SuppressLint("NewApi")
public class DivideRecipeFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_main, container, false);

		final ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		final EditText etRecipeName = (EditText) v
				.findViewById(R.id.et_recipe_name);
		final ListView lvIngredients = (ListView) v
				.findViewById(R.id.lv_ingredients);
		final IngredientInputArrayAdapter ingredientAdapter = new IngredientInputArrayAdapter(
				getActivity(), ingredients);
		lvIngredients.setAdapter(ingredientAdapter);

		final Button btnAddIngredient = (Button) v
				.findViewById(R.id.btn_add_ingredient);
		btnAddIngredient.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ingredients.add(new Ingredient("", 0, getString(R.string.oz)));
				ingredientAdapter.notifyDataSetChanged();
			}
		});

		final Button btnSplitRecipe = (Button) v
				.findViewById(R.id.btn_split_recipe);
		btnSplitRecipe.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				final Intent intent = new Intent(getActivity(),
						RecipeSplitterActivity.class);
				final String recipeName = etRecipeName.getText().toString();
				intent.putExtra("recipeName", recipeName);
				intent.putParcelableArrayListExtra("ingredients", ingredients);
				startActivity(intent);
			}
		});

		// Add the first ingredient.
		ingredients.add(new Ingredient("", 0, getString(R.string.oz)));
		ingredientAdapter.notifyDataSetChanged();

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}
}
