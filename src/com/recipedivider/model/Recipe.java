package com.recipedivider.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
	private List<Ingredient> mIngredients;
	private double mOriginalServings;
	private double mDesiredServings;
	private int mCookTime;

	public Recipe(List<Ingredient> ingredients, int originalServings, int desiredServings, int cookTime) {
		mIngredients = ingredients;
		mOriginalServings = originalServings;
		mDesiredServings = desiredServings;
		mCookTime = cookTime;
	}

	public List<Ingredient> getDividedIngredients() {
		List<Ingredient> dividedIngredients = new ArrayList<Ingredient>();

		for (Ingredient ingredient : mIngredients) {
			String name = ingredient.getName();
			double quantity = ingredient.getQuantity();
			String units = ingredient.getUnits();

			Ingredient dividedIngredient = new Ingredient(name, quantity, units);
			dividedIngredient.setQuantity((quantity * mOriginalServings) / mDesiredServings);
			dividedIngredients.add(dividedIngredient);
		}

		return dividedIngredients;
	}
}
