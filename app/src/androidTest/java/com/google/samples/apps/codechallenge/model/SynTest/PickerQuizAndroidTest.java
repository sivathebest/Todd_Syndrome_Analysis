

package com.google.samples.apps.codechallenge.model.SynTest;

public class PickerQuizAndroidTest extends AbsQuizAndroidTestCase<PickerQuiz> {

    private static final Integer ANSWER = 100;
    private static final int MIN = 1;
    private static final int MAX = 1000;
    private static final int STEP = 10;

    @Override
    public PickerQuiz getQuiz() {
        return new PickerQuiz(QUESTION, ANSWER, MIN, MAX, STEP, false);
    }

}