package com.recipedivider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseObject;

public class Ingredient implements Parcelable {
	private String mName;
	private int mQuantity;
	private String mUnits;

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

	public void setName(String name) {
		mName = name;
	}

	public int getQuantity() {
		return mQuantity;
	}

	public String getUnits() {
		return mUnits;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mName);
		dest.writeInt(mQuantity);
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
		mQuantity = in.readInt();
		mUnits = in.readString();
	}
}
