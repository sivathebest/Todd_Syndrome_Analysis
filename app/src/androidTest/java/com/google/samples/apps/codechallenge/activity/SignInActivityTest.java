
package com.google.samples.apps.codechallenge.activity;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import com.google.samples.apps.codechallenge.R;
import com.google.samples.apps.codechallenge.helper.PreferencesHelper;
import com.google.samples.apps.codechallenge.model.Avatar;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignInActivityTest {

    private static final String TEST_FIRST_NAME = "Zaphod";
    private static final String TEST_LAST_INITIAL = "B";
    private static final Avatar TEST_AVATAR = Avatar.TWELVE;

    @Rule
    public ActivityTestRule<SignInActivity> mActivityRule =
            new ActivityTestRule<SignInActivity>(SignInActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    PreferencesHelper.signOut(InstrumentationRegistry.getTargetContext());
                }
            };

    @Before
    public void clearPreferences() throws Exception {
        PreferencesHelper.signOut(InstrumentationRegistry.getTargetContext());
    }

    @Test
    public void checkFab_initiallyNotDisplayed() {
        onView(withId(R.id.done)).check(matches(not(isDisplayed())));
    }

    @Test
    public void signIn_withoutFirstNameFailed() {
        inputData(null, TEST_LAST_INITIAL, TEST_AVATAR);
        onView(withId(R.id.done)).check(matches(not(isDisplayed())));
    }

    @Test
    public void signIn_withoutLastInitialFailed() {
        inputData(TEST_FIRST_NAME, null, TEST_AVATAR);
        onView(withId(R.id.done)).check(matches(not(isDisplayed())));
    }

    @Test
    public void signIn_withoutAvatarFailed() {
        inputData(TEST_FIRST_NAME, TEST_LAST_INITIAL, null);
        onView(withId(R.id.done)).check(matches(not(isDisplayed())));
    }

    @Test
    public void signIn_withAllPlayerPreferencesSuccessfully() {
        inputData(TEST_FIRST_NAME, TEST_LAST_INITIAL, TEST_AVATAR);
        onView(withId(R.id.done)).check(matches(isDisplayed()));
    }

    @Test
    public void signIn_performSignIn() {
        inputData(TEST_FIRST_NAME, TEST_LAST_INITIAL, TEST_AVATAR);
        onView(withId(R.id.done)).perform(click());
        assertTrue(PreferencesHelper.isSignedIn(InstrumentationRegistry.getTargetContext()));
    }

    @Test
    public void signIn_withLongLastName() {
        typeAndHideKeyboard(R.id.last_initial, TEST_FIRST_NAME);
        String expectedValue = String.valueOf(TEST_FIRST_NAME.charAt(0));
        onView(withId(R.id.last_initial)).check(matches(withText(expectedValue)));
    }

    private void inputData(String firstName, String lastInitial, Avatar avatar) {
        if (firstName != null) typeAndHideKeyboard(R.id.first_name, firstName);
        if (lastInitial != null) typeAndHideKeyboard(R.id.last_initial, lastInitial);
        if (avatar != null) clickAvatar(avatar);
    }

    private void typeAndHideKeyboard(int targetViewId, String text) {
        onView(withId(targetViewId)).perform(typeText(text), closeSoftKeyboard());
    }

    private void clickAvatar(Avatar avatar) {
        onData(equalTo(avatar))
                .inAdapterView(withId(R.id.avatars))
                .perform(click());
    }

    @Test
    public void firstName_isInitiallyEmpty() {
        editTextIsEmpty(R.id.first_name);
    }

    @Test
    public void lastInitial_isInitiallyEmpty() {
        editTextIsEmpty(R.id.last_initial);
    }

    private void editTextIsEmpty(int id) {
        onView(withId(id)).check(matches(withText(isEmptyOrNullString())));
    }

    @Test
    public void avatar_allDisplayed() {
        checkOnAvatar(isDisplayed());
    }

    @Test
    public void avatar_isEnabled() {
        checkOnAvatar(isEnabled());
    }

    @Test
    public void avatar_notFocusable() {
        checkOnAvatar(not(isFocusable()));
    }

    @Test
    public void avatar_notClickable() {
        checkOnAvatar(not(isClickable()));
    }

    @Test
    public void avatar_noneChecked() {
        checkOnAvatar(not(isChecked()));
    }

    private void checkOnAvatar(Matcher<View> matcher) {
        for (int i = 0; i < Avatar.values().length; i++) {
            onData(equalTo(Avatar.values()[i]))
                    .inAdapterView(withId(R.id.avatars))
                    .check(matches(matcher));
        }
    }
}
