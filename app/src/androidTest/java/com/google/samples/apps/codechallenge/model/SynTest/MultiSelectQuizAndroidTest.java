

package com.google.samples.apps.codechallenge.model.SynTest;

public class MultiSelectQuizAndroidTest extends AbsQuizAndroidTestCase<MultiSelectQuiz> {

    @Override
    public MultiSelectQuiz getQuiz() {
        return new MultiSelectQuiz(QUESTION, INT_ARRAY, STRING_ARRAY, false);
    }
}