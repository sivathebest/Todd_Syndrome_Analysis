

package com.google.samples.apps.codechallenge.widget;

import android.annotation.TargetApi;
import android.os.Build;
import android.transition.Transition;

/**
 * Empty implementation of {@link Transition.TransitionListener}.
 */

@TargetApi(Build.VERSION_CODES.KITKAT)
public abstract class TransitionListenerAdapter implements Transition.TransitionListener {

    @Override
    public void onTransitionStart(Transition transition) { }

    @Override
    public void onTransitionEnd(Transition transition) { }

    @Override
    public void onTransitionCancel(Transition transition) { }

    @Override
    public void onTransitionPause(Transition transition) { }

    @Override
    public void onTransitionResume(Transition transition) { }
}
