

package com.google.samples.apps.codechallenge.model.SynTest;

public class SelectItemQuizAndroidTest extends AbsQuizAndroidTestCase<SelectItemQuiz> {

    @Override
    public SelectItemQuiz getQuiz() {
        return new SelectItemQuiz(QUESTION, INT_ARRAY, STRING_ARRAY, false);
    }
}