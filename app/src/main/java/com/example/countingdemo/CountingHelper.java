package com.example.countingdemo;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;

/**
 * Created by Khurram Shehzad on 31 January 2018
 */

public class CountingHelper implements Runnable {

private final long mDuration;
private final int mStartingValue;
private final int mFinalValue;

private final TextView mTextView;

private final int mValueRange;

private long mProgressTime = 0;
private long mLastUpdateTime = 0;


private Interpolator mInterpolator = new AccelerateInterpolator();

/**
 *
 * @param startingValue The initial value
 * @param finalValue The final value
 * @param duration Duration in milliseconds
 * @param textView The TextView to update with value
 */
public CountingHelper(int startingValue, int finalValue, int duration, TextView textView) {
    mDuration = duration;
    mStartingValue = startingValue;
    mFinalValue = finalValue;
    mTextView = textView;

    mValueRange = mFinalValue - mStartingValue;

    mLastUpdateTime = System.currentTimeMillis();
}

@Override
public void run() {
    long now = System.currentTimeMillis();
    mProgressTime += (now - mLastUpdateTime);
    boolean shouldSchedule = true;
    if (mProgressTime >= mDuration) {
        mProgressTime = mDuration;
        shouldSchedule = false;
    }
    mTextView.setText(String.valueOf(getCurrentValue()));
    if (shouldSchedule) {
        mTextView.postOnAnimation(this);
    }
}

private int getCurrentValue() {
    if (mProgressTime >= mDuration) {
        return mFinalValue;
    }
    return (int) (mStartingValue + (mInterpolator.getInterpolation(
            (float) mProgressTime / (float) mDuration) * mValueRange));
}
}
