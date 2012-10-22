package com.recipedivider.ui;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
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

		final EditText mEtIngredientName = (EditText) convertView.findViewById(R.id.et_ingredient_name);
		final String ingredientN = getContext().getString(R.string.ingredient_n, position + 1);
		mEtIngredientName.setHint(ingredientN);

		mEtIngredientName.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				ingredient.setName(mEtIngredientName.getText().toString());
			}
		});

		final EditText mEtQuantity = (EditText) convertView.findViewById(R.id.et_quantity);
		mEtQuantity.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				try {
					ingredient.setQuantity(Integer.valueOf(mEtQuantity.getText().toString()));
				} catch (NumberFormatException e) {
					// probably empty
				}
			}
		});

		Spinner mSpUnits = (Spinner) convertView.findViewById(R.id.sp_units);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.units_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpUnits.setAdapter(adapter);
		mSpUnits.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
			}
		});

		return convertView;
	}
}
