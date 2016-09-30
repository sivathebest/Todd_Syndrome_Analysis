

package com.google.samples.apps.codechallenge.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.samples.apps.codechallenge.model.Category;
import com.google.samples.apps.codechallenge.model.SynTest.AlphaPickerQuiz;
import com.google.samples.apps.codechallenge.model.SynTest.FillBlankQuiz;
import com.google.samples.apps.codechallenge.model.SynTest.FillTwoBlanksQuiz;
import com.google.samples.apps.codechallenge.model.SynTest.FourQuarterQuiz;
import com.google.samples.apps.codechallenge.model.SynTest.MultiSelectQuiz;
import com.google.samples.apps.codechallenge.model.SynTest.PickerQuiz;
import com.google.samples.apps.codechallenge.model.SynTest.Quiz;
import com.google.samples.apps.codechallenge.model.SynTest.SelectItemQuiz;
import com.google.samples.apps.codechallenge.model.SynTest.ToggleTranslateQuiz;
import com.google.samples.apps.codechallenge.model.SynTest.TrueFalseQuiz;
import com.google.samples.apps.codechallenge.widget.quiz.AbsQuizView;
import com.google.samples.apps.codechallenge.widget.quiz.AlphaPickerQuizView;
import com.google.samples.apps.codechallenge.widget.quiz.FillBlankQuizView;
import com.google.samples.apps.codechallenge.widget.quiz.FillTwoBlanksQuizView;
import com.google.samples.apps.codechallenge.widget.quiz.FourQuarterQuizView;
import com.google.samples.apps.codechallenge.widget.quiz.MultiSelectQuizView;
import com.google.samples.apps.codechallenge.widget.quiz.PickerQuizView;
import com.google.samples.apps.codechallenge.widget.quiz.SelectItemQuizView;
import com.google.samples.apps.codechallenge.widget.quiz.ToggleTranslateQuizView;
import com.google.samples.apps.codechallenge.widget.quiz.TrueFalseQuizView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Adapter to display quizzes.
 */
public class QuizAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Quiz> mQuizzes;
    private final Category mCategory;
    private final int mViewTypeCount;
    private List<String> mQuizTypes;

    public QuizAdapter(Context context, Category category) {
        mContext = context;
        mCategory = category;
        mQuizzes = category.getQuizzes();
        mViewTypeCount = calculateViewTypeCount();

    }

    private int calculateViewTypeCount() {
        Set<String> tmpTypes = new HashSet<>();
        for (int i = 0; i < mQuizzes.size(); i++) {
            tmpTypes.add(mQuizzes.get(i).getType().getJsonName());
        }
        mQuizTypes = new ArrayList<>(tmpTypes);
        return mQuizTypes.size();
    }

    @Override
    public int getCount() {
        return mQuizzes.size();
    }

    @Override
    public Quiz getItem(int position) {
        return mQuizzes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mQuizzes.get(position).getId();
    }

    @Override
    public int getViewTypeCount() {
        return mViewTypeCount;
    }

    @Override
    public int getItemViewType(int position) {
        return mQuizTypes.indexOf(getItem(position).getType().getJsonName());
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Quiz quiz = getItem(position);
        if (convertView instanceof AbsQuizView) {
            if (((AbsQuizView) convertView).getQuiz().equals(quiz)) {
                return convertView;
            }
        }
        convertView = getViewInternal(quiz);
        return convertView;
    }

    private AbsQuizView getViewInternal(Quiz quiz) {
        if (null == quiz) {
            throw new IllegalArgumentException("Quiz must not be null");
        }
        return createViewFor(quiz);
    }

    private AbsQuizView createViewFor(Quiz quiz) {
        switch (quiz.getType()) {
            case ALPHA_PICKER:
                return new AlphaPickerQuizView(mContext, mCategory, (AlphaPickerQuiz) quiz);
            case FILL_BLANK:
                return new FillBlankQuizView(mContext, mCategory, (FillBlankQuiz) quiz);
            case FILL_TWO_BLANKS:
                return new FillTwoBlanksQuizView(mContext, mCategory, (FillTwoBlanksQuiz) quiz);
            case FOUR_QUARTER:
                return new FourQuarterQuizView(mContext, mCategory, (FourQuarterQuiz) quiz);
            case MULTI_SELECT:
                return new MultiSelectQuizView(mContext, mCategory, (MultiSelectQuiz) quiz);
            case PICKER:
                return new PickerQuizView(mContext, mCategory, (PickerQuiz) quiz);
            case SINGLE_SELECT:
            case SINGLE_SELECT_ITEM:
                return new SelectItemQuizView(mContext, mCategory, (SelectItemQuiz) quiz);
            case TOGGLE_TRANSLATE:
                return new ToggleTranslateQuizView(mContext, mCategory,
                        (ToggleTranslateQuiz) quiz);
            case TRUE_FALSE:
                return new TrueFalseQuizView(mContext, mCategory, (TrueFalseQuiz) quiz);
        }
        throw new UnsupportedOperationException(
                "Quiz of type " + quiz.getType() + " can not be displayed.");
    }
}
