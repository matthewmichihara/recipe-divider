package com.recipedivider.ui;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.recipedivider.R;
import com.recipedivider.model.Ingredient;

@SuppressLint("NewApi")
public class DivideRecipeFragment extends Fragment {
	private ArrayList<Ingredient> mIngredients = new ArrayList<Ingredient>();
	private IngredientInputArrayAdapter mIngredientAdapter;
	private EditText mEtRecipeName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);

		View v = inflater.inflate(R.layout.activity_main, container, false);

		mEtRecipeName = (EditText) v.findViewById(R.id.et_recipe_name);
		final ListView lvIngredients = (ListView) v
				.findViewById(R.id.lv_ingredients);

		View addIngredientFooter = inflater.inflate(
				R.layout.list_item_add_ingredient_footer, null);
		lvIngredients.addFooterView(addIngredientFooter);

		mIngredientAdapter = new IngredientInputArrayAdapter(getActivity(),
				mIngredients);
		lvIngredients.setAdapter(mIngredientAdapter);

		final ImageView btnAddIngredient = (ImageView) v
				.findViewById(R.id.btn_add_ingredient);
		btnAddIngredient.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mIngredients.add(new Ingredient("", 0, getString(R.string.oz)));
				mIngredientAdapter.notifyDataSetChanged();
			}
		});

		// Add the first ingredient.
		mIngredients.add(new Ingredient("", 0, getString(R.string.oz)));
		mIngredientAdapter.notifyDataSetChanged();

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.fragment_divide_recipe, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.item_next) {
			final Intent intent = new Intent(getActivity(),
					RecipeSplitterActivity.class);
			final String recipeName = mEtRecipeName.getText().toString();
			intent.putExtra("recipeName", recipeName);
			intent.putParcelableArrayListExtra("ingredients", mIngredients);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
