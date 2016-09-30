

package com.google.samples.apps.codechallenge.model;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class PlayerAndroidTest {

    private static final Avatar AVATAR = Avatar.TWELVE;
    private static final String LAST_INITIAL = "a";
    private static final String FIRST_NAME = "first";

    private static Player getPlayerUnderTest() {
        return new Player(FIRST_NAME, LAST_INITIAL, AVATAR);
    }

    @Test
    public void writeToParcel() throws Exception {
        Player initial = getPlayerUnderTest();
        Parcel dest = Parcel.obtain();
        initial.writeToParcel(dest, 0);
        dest.setDataPosition(0);
        Player unparcelled = new Player(dest);
        assertThat(initial, is(unparcelled));
    }
}