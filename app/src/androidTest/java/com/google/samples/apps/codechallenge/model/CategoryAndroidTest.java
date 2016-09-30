

package com.google.samples.apps.codechallenge.model;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.google.samples.apps.codechallenge.model.SynTest.FillBlankQuiz;
import com.google.samples.apps.codechallenge.model.SynTest.Quiz;
import com.google.samples.apps.codechallenge.model.SynTest.TrueFalseQuiz;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class CategoryAndroidTest {

    private static final String CATEGORY_NAME = "test";
    private static final String CATEGORY_ID = "testId";
    private static final Theme CATEGORY_THEME = Theme.topeka;

    private static Category getCategoryUnderTest() {
        return new Category(CATEGORY_NAME, CATEGORY_ID, CATEGORY_THEME, getQuizzes(), false);
    }

    private static List<Quiz> getQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(new TrueFalseQuiz("huh?", true, false));
        quizzes.add(new FillBlankQuiz("so?", "yeah", "go", "stop", false));
        return quizzes;
    }

    @Test
    public void writeToParcel() {
        Category initialCategory = getCategoryUnderTest();
        Parcel dest = Parcel.obtain();
        initialCategory.writeToParcel(dest, 0);
        dest.setDataPosition(0);
        Category unparcelled = new Category(dest);
        assertThat(initialCategory, is(unparcelled));
    }
}