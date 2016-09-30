

package com.google.samples.apps.codechallenge.helper;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.SparseBooleanArray;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests whether answers are being correctly stored and retrieved.
 */
@SmallTest
@RunWith(AndroidJUnit4.class)
public class AnswerHelperAndroidTest {

    private static final int[] OPTIONS = new int[]{0, 1, 2};
    private final int[] WRONG_OPTIONS = new int[]{2, 1};
    private SparseBooleanArray mCorrectAnswer;

    @Before
    public void setCorrectAnswers() {
        mCorrectAnswer = new SparseBooleanArray();
        mCorrectAnswer.put(0, true);
        mCorrectAnswer.put(1, true);
        mCorrectAnswer.put(2, true);
    }

    @Test
    public void partial_isAnswerCorrect_returnsTrue() throws Exception {
        assertThat(AnswerHelper.isAnswerCorrect(mCorrectAnswer, OPTIONS), is(true));
    }

    @Test
    public void partial_isAnswerCorrect_returnsFalse() throws Exception {
        assertThat(AnswerHelper.isAnswerCorrect(mCorrectAnswer, WRONG_OPTIONS), is(false));
    }
}