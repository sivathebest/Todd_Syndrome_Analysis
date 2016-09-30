

package com.google.samples.apps.codechallenge.model.SynTest;

public class FillBlankQuizAndroidTest extends AbsQuizAndroidTestCase<FillBlankQuiz> {

    private static final String ANSWER = "answer";
    private static final String START = "start";
    private static final String END = "end";

    @Override
    public FillBlankQuiz getQuiz() {
        return new FillBlankQuiz(QUESTION, ANSWER, START, END, false);
    }
}