package com.recipedivider.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {
	private String mName;
	private double mQuantity;
	private String mUnits;

	public Ingredient(final String name, final double quantity, final String units) {
		mName = name;
		mQuantity = quantity;
		mUnits = units;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public double getQuantity() {
		return mQuantity;
	}

	public void setQuantity(double quantity) {
		mQuantity = quantity;
	}

	public String getUnits() {
		return mUnits;
	}

	public void setUnits(String units) {
		mUnits = units;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mName);
		dest.writeDouble(mQuantity);
		dest.writeString(mUnits);
	}

	public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
		@Override
		public Ingredient createFromParcel(Parcel in) {
			return new Ingredient(in);
		}

		@Override
		public Ingredient[] newArray(int size) {
			return new Ingredient[size];
		}
	};

	private Ingredient(Parcel in) {
		mName = in.readString();
		mQuantity = in.readDouble();
		mUnits = in.readString();
	}
}
