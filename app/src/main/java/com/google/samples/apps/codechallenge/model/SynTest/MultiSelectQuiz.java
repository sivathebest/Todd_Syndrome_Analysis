

package com.google.samples.apps.codechallenge.model.SynTest;


import android.annotation.SuppressLint;
import android.os.Parcel;

import com.google.samples.apps.codechallenge.helper.AnswerHelper;

@SuppressLint("ParcelCreator")
public final class MultiSelectQuiz extends OptionsQuiz<String> {

    public MultiSelectQuiz(String question, int[] answer, String[] options, boolean solved) {
        super(question, answer, options, solved);
    }

    @SuppressWarnings("unused")
    public MultiSelectQuiz(Parcel in) {
        super(in);
        String options[] = in.createStringArray();
        setOptions(options);
    }

    @Override
    public QuizType getType() {
        return QuizType.MULTI_SELECT;
    }

    @Override
    public String getStringAnswer() {
        return AnswerHelper.getAnswer(getAnswer(), getOptions());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeStringArray(getOptions());
    }
}
