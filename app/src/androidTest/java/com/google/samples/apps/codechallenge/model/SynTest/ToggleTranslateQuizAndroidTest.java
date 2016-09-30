

package com.google.samples.apps.codechallenge.model.SynTest;

public class ToggleTranslateQuizAndroidTest extends AbsQuizAndroidTestCase<ToggleTranslateQuiz> {

    private static final String[][] OPTIONS = new String[][]{STRING_ARRAY, STRING_ARRAY};

    @Override
    public ToggleTranslateQuiz getQuiz() {
        return new ToggleTranslateQuiz(QUESTION, INT_ARRAY, OPTIONS, false);
    }
}