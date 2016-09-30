

package com.google.samples.apps.codechallenge.helper;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests whether the parcelable helper stores and retrieves data correctly.
 */
@SmallTest
@RunWith(AndroidJUnit4.class)
public class ParcelableHelperAndroidTest {

    @Test
    public void writeReadBoolean() throws Exception {
        Parcel testParcel = Parcel.obtain();
        final boolean testValue = true;
        ParcelableHelper.writeBoolean(testParcel, testValue);
        testParcel.setDataPosition(0);
        final boolean resultValue = ParcelableHelper.readBoolean(testParcel);
        assertThat(testValue, is(resultValue));
    }
}