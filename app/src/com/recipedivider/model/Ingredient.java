package com.recipedivider.model;

import com.parse.ParseObject;

public class Ingredient {
	private final String mName;
	private final int mQuantity;
	private final String mUnits;

	public Ingredient(final String name, final int quantity, final String units) {
		mName = name;
		mQuantity = quantity;
		mUnits = units;
	}

	public Ingredient(final ParseObject object) {
		mName = object.getString("name");
		mQuantity = object.getInt("quantity");
		mUnits = object.getString("units");
	}

	public String getName() {
		return mName;
	}

	public int getQuantity() {
		return mQuantity;
	}

	public String getUnits() {
		return mUnits;
	}
}
