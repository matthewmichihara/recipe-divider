package com.recipedivider.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.recipedivider.R;

public class RecipeBoxActivity extends Activity {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_box);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.activity_recipe_box, menu);
		return true;
	}
}
