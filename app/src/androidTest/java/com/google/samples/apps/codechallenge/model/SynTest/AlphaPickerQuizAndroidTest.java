

package com.google.samples.apps.codechallenge.model.SynTest;

public class AlphaPickerQuizAndroidTest extends AbsQuizAndroidTestCase<AlphaPickerQuiz> {

    private static final String ANSWER = "answer";

    @Override
    public AlphaPickerQuiz getQuiz() {
        return new AlphaPickerQuiz(QUESTION, ANSWER, false);
    }

}