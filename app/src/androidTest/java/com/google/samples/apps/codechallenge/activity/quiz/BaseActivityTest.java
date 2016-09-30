

package com.google.samples.apps.codechallenge.activity.quiz;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.google.samples.apps.codechallenge.R;
import com.google.samples.apps.codechallenge.activity.QuizActivity;
import com.google.samples.apps.codechallenge.helper.PreferencesHelper;
import com.google.samples.apps.codechallenge.SolveQuizUtil;
import com.google.samples.apps.codechallenge.model.Avatar;
import com.google.samples.apps.codechallenge.model.Category;
import com.google.samples.apps.codechallenge.model.Player;
import com.google.samples.apps.codechallenge.model.SynTest.Quiz;
import com.google.samples.apps.codechallenge.persistence.CodeChallengeDatabaseHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
/**
 * Parent class for all quiz activity tests.
 */
public abstract class BaseActivityTest {

    @Rule
    public final ActivityTestRule<QuizActivity> mActivityRule =
            new ActivityTestRule<QuizActivity>(QuizActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    Context targetContext = InstrumentationRegistry.getTargetContext();
                    PreferencesHelper.signOut(targetContext);
                    CodeChallengeDatabaseHelper.reset(targetContext);
                    PreferencesHelper.writeToPreferences(targetContext,
                            new Player("Zaphod", "B", Avatar.FIVE));
                }

                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getTargetContext();
                    mCategories = CodeChallengeDatabaseHelper.getCategories(targetContext, false);
                    return QuizActivity.getStartIntent(targetContext,
                            getCurrentCategory());
                }
            };

    private List<Category> mCategories;

    /**
     * @return The category's position.
     */
    abstract int getCategory();

    /**
     * Register idling resources for the activity under test.
     */
    @Before
    public void registerIdlingResources() {
        Espresso.registerIdlingResources(mActivityRule.getActivity().getCountingIdlingResource());
    }

    /**
     * Unregister idling resources for the activity under test.
     */
    @After
    public void unregisterIdlingResources() {
        Espresso.unregisterIdlingResources(mActivityRule.getActivity().getCountingIdlingResource());
    }

    /**
     * Tests whether a category with it's given name is currently didsplayed.
     */
    @Test
    public void categoryName_isDisplayed() {
        onView(withText(getCurrentCategory().getName())).check(matches(isDisplayed()));
    }

    /**
     * Presses back from within the toolbar.
     */
    @Test
    public void goBack_fromToolbar() {
        onView(withId(R.id.back)).perform(click());
    }

    @Test
    public void category_solveCorrectly() {
        testCategory();
    }

    /**
     * End to end test for the given category.
     */
    protected void testCategory() {
        final Category category = getCurrentCategory();
        onView(withId(R.id.fab_quiz)).perform(click());
        for (Quiz quiz : category.getQuizzes()) {
            SolveQuizUtil.solveQuiz(quiz);
            onView(allOf(withId(R.id.submitAnswer), isDisplayed()))
                    .check(matches(isDisplayed()))
                    .perform(click());
        }
    }

    private Category getCurrentCategory() {
        return mCategories.get(getCategory());
    }

}
