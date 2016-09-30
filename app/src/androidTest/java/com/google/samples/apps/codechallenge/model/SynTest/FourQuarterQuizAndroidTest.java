

package com.google.samples.apps.codechallenge.model.SynTest;

public class FourQuarterQuizAndroidTest extends AbsQuizAndroidTestCase<FourQuarterQuiz> {

    @Override
    public FourQuarterQuiz getQuiz() {
        return new FourQuarterQuiz(QUESTION, INT_ARRAY, STRING_ARRAY, false);
    }
}