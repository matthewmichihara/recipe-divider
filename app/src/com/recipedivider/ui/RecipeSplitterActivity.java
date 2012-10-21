package com.recipedivider.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.recipedivider.R;

public class RecipeSplitterActivity extends Activity {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_splitter);

		final Button btnCalculate = (Button) findViewById(R.id.btn_calculate);
		btnCalculate.setOnClickListener(new OnClickListener() {
			public void onClick(final View v) {
				final Intent intent = new Intent(RecipeSplitterActivity.this,
						RecipeResultsActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.activity_recipe_splitter, menu);
		return true;
	}
}
