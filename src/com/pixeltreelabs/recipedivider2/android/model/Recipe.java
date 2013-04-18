package com.pixeltreelabs.recipedivider2.android.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable {
	private String mName;
	private List<Ingredient> mIngredients;
	private double mServings;
	private int mCookTime;

	public Recipe(String name, List<Ingredient> ingredients, double servings, int cookTime) {
		mName = name;
		mIngredients = ingredients;
		mServings = servings;
		mCookTime = cookTime;
	}

	public String getName() {
		return mName;
	}

	public List<Ingredient> getIngredients() {
		return mIngredients;
	}

	public Recipe getDividedRecipe(double desiredServings) {
		List<Ingredient> dividedIngredients = new ArrayList<Ingredient>();
		for (Ingredient ingredient : mIngredients) {
			String name = ingredient.getName();
			double dividedQuantity = (ingredient.getQuantity() * desiredServings) / mServings;
			String units = ingredient.getUnits();
			dividedIngredients.add(new Ingredient(name, dividedQuantity, units));
		}

		int dividedCookTime = (int) ((mCookTime * desiredServings) / mServings);

		return new Recipe(mName, dividedIngredients, desiredServings, dividedCookTime);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mName);
		dest.writeTypedList(mIngredients);
		dest.writeDouble(mServings);
		dest.writeInt(mCookTime);
	}

	public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
		@Override
		public Recipe createFromParcel(Parcel in) {
			return new Recipe(in);
		}

		@Override
		public Recipe[] newArray(int size) {
			return new Recipe[size];
		}
	};

	private Recipe(Parcel in) {
		mName = in.readString();
		mIngredients = new ArrayList<Ingredient>();
		in.readTypedList(mIngredients, Ingredient.CREATOR);
		mServings = in.readDouble();
		mCookTime = in.readInt();
	}
}
