package com.pixeltreelabs.recipedivider2.android.ui;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.pixeltreelabs.recipedivider2.android.R;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Tab divideRecipeTab = actionBar
				.newTab()
				.setText(R.string.divide_recipe)
				.setIcon(R.drawable.ic_recipe_divide_tab)
				.setTabListener(
						new TabListener<DivideRecipeFragment>(this,
								"divide_recipe", DivideRecipeFragment.class));
		actionBar.addTab(divideRecipeTab);

		Tab recipeBoxTab = actionBar
				.newTab()
				.setText(R.string.recipe_box)
				.setIcon(R.drawable.ic_recipe_box_tab)
				.setTabListener(
						new TabListener<RecipeBoxFragment>(this, "recipe_box",
								RecipeBoxFragment.class));
		actionBar.addTab(recipeBoxTab);

		// Set the view to the recipe box tab if extra says we should.
		boolean openRecipeBoxTab = getIntent().getBooleanExtra(
				"open_recipe_box_tab", false);
		if (openRecipeBoxTab) {
			actionBar.setSelectedNavigationItem(recipeBoxTab.getPosition());
		}
	}

	public static class TabListener<T extends Fragment> implements
			ActionBar.TabListener {
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;

		/**
		 * Constructor used each time a new tab is created.
		 * 
		 * @param activity
		 *            The host Activity, used to instantiate the fragment
		 * @param tag
		 *            The identifier tag for the fragment
		 * @param clz
		 *            The fragment's Class, used to instantiate the fragment
		 */
		public TabListener(Activity activity, String tag, Class<T> clz) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
		}

		/* The following are each of the ActionBar.TabListener callbacks */

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// Check if the fragment is already initialized
			if (mFragment == null) {
				// If not, instantiate and add it to the activity
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				ft.add(android.R.id.content, mFragment, mTag);
			} else {
				// If it exists, simply attach it in order to show it
				ft.attach(mFragment);
			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				// Detach the fragment, because another one is being attached
				ft.detach(mFragment);
			}
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}
	}
}
