package com.pixeltreelabs.recipedivider2.android.ui;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pixeltreelabs.recipedivider2.android.R;
import com.pixeltreelabs.recipedivider2.android.model.Ingredient;

public class IngredientArrayAdapter extends ArrayAdapter<Ingredient> {

	public IngredientArrayAdapter(final Context context,
			final List<Ingredient> ingredients) {
		super(context, R.layout.list_item_ingredient, ingredients);
	}

	@Override
	public View getView(final int position, View convertView,
			final ViewGroup parent) {
		if (convertView == null) {
			final LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.list_item_ingredient, null);
		}

		final Ingredient ingredient = getItem(position);
		final double quantity = ingredient.getQuantity();
		final String units = ingredient.getUnits();
		final String name = ingredient.getName();

		DecimalFormat df = new DecimalFormat("#.##");

		final String ingredientText = getContext().getString(
				R.string.quantity_unit_ingredient, df.format(quantity), units,
				name);

		final TextView tvIngredient = (TextView) convertView
				.findViewById(R.id.tv_ingredient);
		tvIngredient.setText(ingredientText);

		return convertView;
	}
}
