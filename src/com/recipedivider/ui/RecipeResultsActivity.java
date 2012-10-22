package com.recipedivider.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.recipedivider.R;

public class RecipeResultsActivity extends Activity {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_results);

		final Button btnShare = (Button) findViewById(R.id.btn_share);
		btnShare.setOnClickListener(new OnClickListener() {
			public void onClick(final View v) {
				final Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
				intent.putExtra(Intent.EXTRA_SUBJECT, "Hello World");
				intent.putExtra(Intent.EXTRA_TEXT,
						"Check out this awesome recipe");
				startActivity(Intent.createChooser(intent, "Share your recipe"));
			}
		});

		final Button btnSaveToRecipeBox = (Button) findViewById(R.id.btn_save_to_recipe_box);
		btnSaveToRecipeBox.setOnClickListener(new OnClickListener() {
			public void onClick(final View v) {
				final Intent intent = new Intent(RecipeResultsActivity.this,
						RecipeBoxActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.activity_recipe_results, menu);
		return true;
	}
}