package com.recipedivider.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.recipedivider.R;
import com.recipedivider.model.Recipe;

public class RecipeBoxActivity extends Activity {
	private static final String TAG = RecipeBoxActivity.class.getSimpleName();

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_box);

		final Intent intent = getIntent();
		final Recipe recipe = intent.getParcelableExtra("recipe");

		Log.d(TAG, "recipe: " + recipe);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.activity_recipe_box, menu);
		return true;
	}
}
