package com.recipedivider.ui;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.recipedivider.R;
import com.recipedivider.model.Ingredient;

public class IngredientArrayAdapter extends ArrayAdapter<Ingredient> {

	public IngredientArrayAdapter(final Context context, final List<Ingredient> ingredients) {
		super(context, R.layout.list_item_ingredient, ingredients);
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		if (convertView == null) {
			final LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.list_item_ingredient, null);
		}

		final TextView tvIngredientN = (TextView) convertView.findViewById(R.id.tv_ingredient_n);
		final String ingredientN = getContext().getString(R.string.ingredient_n, position + 1);
		tvIngredientN.setText(ingredientN);

		return convertView;
	}

}
