

package com.google.samples.apps.codechallenge.model.SynTest;

public class FillTwoBlanksQuizAndroidTest extends AbsQuizAndroidTestCase<FillTwoBlanksQuiz> {

    @Override
    public FillTwoBlanksQuiz getQuiz() {
        return new FillTwoBlanksQuiz(QUESTION, STRING_ARRAY, false);
    }
}