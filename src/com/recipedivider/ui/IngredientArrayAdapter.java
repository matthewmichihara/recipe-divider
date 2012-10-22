package com.recipedivider.ui;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.recipedivider.R;
import com.recipedivider.model.Ingredient;

public class IngredientArrayAdapter extends ArrayAdapter<Ingredient> {

	private static final String TAG = IngredientArrayAdapter.class.getSimpleName();

	public IngredientArrayAdapter(final Context context, final List<Ingredient> ingredients) {
		super(context, R.layout.list_item_ingredient, ingredients);
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		if (convertView == null) {
			final LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.list_item_ingredient, null);
		}

		final Ingredient ingredient = getItem(position);

		final EditText etIngredientName = (EditText) convertView.findViewById(R.id.et_ingredient_name);
		final String ingredientN = getContext().getString(R.string.ingredient_n, position + 1);
		etIngredientName.setHint(ingredientN);

		etIngredientName.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				ingredient.setName(etIngredientName.getText().toString());
			}
		});

		final EditText etQuantity = (EditText) convertView.findViewById(R.id.et_quantity);
		etQuantity.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				try {
					ingredient.setQuantity(Integer.valueOf(etQuantity.getText().toString()));
				} catch (NumberFormatException e) {
					Log.w(TAG, "Error", e);
				}
			}
		});

		final Spinner spUnits = (Spinner) convertView.findViewById(R.id.sp_units);
		final ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.units_array, android.R.layout.simple_spinner_item);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spUnits.setAdapter(spinnerAdapter);
		spUnits.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				ingredient.setUnits((String) arg0.getItemAtPosition(arg2));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		return convertView;
	}
}
