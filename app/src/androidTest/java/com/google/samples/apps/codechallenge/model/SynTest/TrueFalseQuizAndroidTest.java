

package com.google.samples.apps.codechallenge.model.SynTest;

public class TrueFalseQuizAndroidTest extends AbsQuizAndroidTestCase<TrueFalseQuiz> {

    private static final boolean ANSWER = true;

    @Override
    public TrueFalseQuiz getQuiz() {
        return new TrueFalseQuiz(QUESTION, ANSWER, false);
    }
}