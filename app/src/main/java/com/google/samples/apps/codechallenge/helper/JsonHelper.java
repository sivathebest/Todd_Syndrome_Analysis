

package com.google.samples.apps.codechallenge.helper;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Helper class to make unsafe types safe to use in the java world.
 */
public class JsonHelper {

    private static final String TAG = "JsonHelper";

    private JsonHelper() {
        //no instance
    }

    /**
     * Creates a String array out of a json array.
     *
     * @param json The String containing the json array.
     * @return An array with the extracted strings or an
     * empty String array if an exception occurred.
     */
    public static String[] jsonArrayToStringArray(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            String[] stringArray = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                stringArray[i] = jsonArray.getString(i);
            }
            return stringArray;
        } catch (JSONException e) {
            Log.e(TAG, "Error during Json processing: ", e);
        }
        return new String[0];
    }

    /**
     * Creates an int array out of a json array.
     *
     * @param json The String containing the json array.
     * @return An array with the extracted integers or an
     * empty int array if an exception occurred.
     */
    public static int[] jsonArrayToIntArray(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            int[] intArray = new int[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                intArray[i] = jsonArray.getInt(i);
            }
            return intArray;
        } catch (JSONException e) {
            Log.e(TAG, "Error during Json processing: ", e);
        }
        return new int[0];
    }
}
