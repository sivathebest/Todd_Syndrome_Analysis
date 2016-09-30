

package com.google.samples.apps.codechallenge.activity;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.google.samples.apps.codechallenge.R;
import com.google.samples.apps.codechallenge.helper.PreferencesHelper;
import com.google.samples.apps.codechallenge.model.Avatar;
import com.google.samples.apps.codechallenge.model.Category;
import com.google.samples.apps.codechallenge.model.Player;
import com.google.samples.apps.codechallenge.persistence.CodeChallengeDatabaseHelper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CategorySelectionActivityTest {

    private Context mTargetContext;
    private List<Category> mCategories;

    @Rule
    public ActivityTestRule<CategorySelectionActivity> mActivityRule =
            new ActivityTestRule<CategorySelectionActivity>(CategorySelectionActivity.class) {

                @Override
                protected void beforeActivityLaunched() {
                    PreferencesHelper.signOut(InstrumentationRegistry.getTargetContext());
                }

                @Override
                protected Intent getActivityIntent() {
                    mTargetContext = InstrumentationRegistry.getTargetContext();
                    final Player player = new Player("Zaphod", "B", Avatar.EIGHT);
                    return CategorySelectionActivity.getStartIntent(mTargetContext, player);
                }
            };

    @Before
    public void loadCategories() {
        mCategories = CodeChallengeDatabaseHelper.getCategories(mTargetContext, false);
    }

    @Test
    public void allCategories_areDisplayed() throws InterruptedException {
        String categoryName;
        for (int i = 0; i < mCategories.size(); i++) {
            categoryName = mCategories.get(i).getName();
            onView(withId(R.id.categories))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, scrollTo()));
            onView(withText(categoryName)).check(matches(isDisplayed()));

        }
    }

    @Test
    public void signOut() {
        openActionBarOverflowOrOptionsMenu(mTargetContext);
        onView(withText(R.string.sign_out)).perform(click());
        assertFalse(PreferencesHelper.isSignedIn(mTargetContext));
    }
}
