/*
 *
 *  * Copyright 2016 Y Media Labs. All rights reserved.
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 *
 */
package com.droidpro.foodbuddy.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.droidpro.foodbuddy.constants.FbdConstants;

import java.util.Set;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


/**
 * Created by santosh on 10/10/16.
 */

public class PreferenceManager {

    /**
     * Checks whether the preferences contains a key or not
     *
     * @param context
     * @param key
     * @return <code>true</code> if the key exists, <code>false</code> otherwise
     */
    public static boolean contains(final Context context, final String key) {
        final SharedPreferences preferences = getDefaultSharedPreferences(context);
        return preferences.contains(key);
    }

    /**
     * Get String value for a particular key.
     * getImageFromFile
     *
     * @param context
     * @param key
     * @return String value that was stored earlier, or empty string if no
     * mapping exists
     */
    @Nullable
    public static String getString(final Context context, final String key) {

        return getString(context, key, FbdConstants.EMPTY);
    }

    /**
     * Get String value for a particular key.
     *
     * @param context
     * @param key
     * @param defValue The default value to return
     * @return String value that was stored earlier, or the supplied default
     * value if no mapping exists
     */
    @Nullable
    public static String getString(final Context context, final String key, final String defValue) {

        final SharedPreferences preferences = getDefaultSharedPreferences(context);
//        String decryptValue = preferences.getString(key, defValue);
//        return AwbKeyGenerator.decryptString(context, decryptValue);
        return preferences.getString(key, defValue);
    }

    /**
     * Get String set for a particular key.
     *
     * @param context
     * @param key
     * @param defValue The default value to return
     * @return String value that was stored earlier, or the supplied default
     * value if no mapping exists
     */
    @Nullable
    public static Set<String> getStringSet(final Context context, final String key, final Set<String> defValue) {

        final SharedPreferences preferences = getDefaultSharedPreferences(context);
        return preferences.getStringSet(key, defValue);
    }

    /**
     * Get int value for key.
     *
     * @param context
     * @param key
     * @return value or 0 if no mapping exists
     */
    public static int getInt(final Context context, final String key) {

        return getInt(context, key, 0);
    }

    /**
     * Get int value for key.
     *
     * @param context
     * @param key
     * @param defValue The default value
     * @return value or defValue if no mapping exists
     */
    public static int getInt(final Context context, final String key, final int defValue) {

        final SharedPreferences preferences = getDefaultSharedPreferences(context);
//        String decryptValue = preferences.getString(key, "0");
//        if (decryptValue.equals("0")) {
//            return 0;
//        } else {
//            return Integer.parseInt(AwbKeyGenerator.decryptString(context, decryptValue));
//        }
        return preferences.getInt(key, defValue);
    }

    /**
     * Get float value for a particular key.
     *
     * @param context
     * @param key
     * @return value or 0.0 if no mapping exists
     */
    public static float getFloat(final Context context, final String key) {

        return getFloat(context, key, 0.0f);

    }

    /**
     * Get float value for a particular key.
     *
     * @param context
     * @param key
     * @param defValue
     * @return value or defValue if no mapping exists
     */
    public static float getFloat(final Context context, final String key, final float defValue) {

        final SharedPreferences preferences = getDefaultSharedPreferences(context);
//        String decryptValue = preferences.getString(key, "0.0f");
//        if (decryptValue.equals("0.0f")) {
//            return 0.0f;
//        } else {
//            return Float.parseFloat(AwbKeyGenerator.decryptString(context, decryptValue));
//        }
        return preferences.getFloat(key, defValue);
    }

    /**
     * Get long value for a particular key.
     *
     * @param context
     * @param key
     * @return value or 0 if no mapping exists
     */
    public static long getLong(final Context context, final String key) {

        return getLong(context, key, 0L);
    }

    /**
     * Get long value for a particular key.
     *
     * @param context
     * @param key
     * @param defValue
     * @return value or defValue if no mapping exists
     */
    public static long getLong(final Context context, final String key, final long defValue) {

        final SharedPreferences preferences = getDefaultSharedPreferences(context);
//        String decryptValue = preferences.getString(key, "0L");
//        if (decryptValue.equals("0L")) {
//            return 0L;
//        } else {
//            return Long.parseLong(AwbKeyGenerator.decryptString(context, decryptValue));
//        }
        return preferences.getLong(key, defValue);
    }

    /**
     * Get boolean value for a particular key.
     *
     * @param context
     * @param key
     * @return value or false if no mapping exists
     */
    public static boolean getBoolean(final Context context, final String key) {

        return getBoolean(context, key, false);
    }

    /**
     * Get boolean value for a particular key.
     *
     * @param context
     * @param key
     * @param defValue
     * @return value or defValue if no mapping exists
     */
    public static boolean getBoolean(final Context context, final String key, final boolean defValue) {

        final SharedPreferences preferences = getDefaultSharedPreferences(context);
//        String decryptValue = preferences.getString(key, "false");
//        if (decryptValue.equals("false")) {
//            return false;
//        } else {
//            return Boolean.parseBoolean(AwbKeyGenerator.decryptString(context, decryptValue));
//        }
        return preferences.getBoolean(key, defValue);
    }

    /**
     * Set String value for a particular key. Convert non-Strings to appropriate
     * Strings before storing.
     *
     * @param context
     * @param key
     * @param value
     */
    public static void set(final Context context, final String key, final String value) {

        final SharedPreferences preferences = getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
//        String encryptedValue = AwbKeyGenerator.encryptString(context, key, value);
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Set String list value for a particular key. Convert non-Strings to appropriate
     * Strings before storing.
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setStringList(final Context context, final String key, final Set<String> value) {

        final SharedPreferences preferences = getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    /**
     * Set int value for key.
     *
     * @param context
     * @param key
     * @param value
     */
    public static void set(final Context context, final String key, final int value) {

        final SharedPreferences preferences = getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
//        String encryptedValue = AwbKeyGenerator.encryptString(context, key, String.valueOf(value));
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Set float value for a key.
     *
     * @param context
     * @param key
     * @param value
     */
    public static void set(final Context context, final String key, final float value) {

        final SharedPreferences preferences = getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
//        String encryptedValue = AwbKeyGenerator.encryptString(context, key, String.valueOf(value));
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * Set long value for key.
     *
     * @param context
     * @param key
     * @param value
     */
    public static void set(final Context context, final String key, final long value) {

        final SharedPreferences preferences = getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
//        String encryptedValue = AwbKeyGenerator.encryptString(context, key, String.valueOf(value));
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * Set boolean value for key.
     *
     * @param context
     * @param key
     * @param value
     */
    public static void set(final Context context, final String key, final boolean value) {

        final SharedPreferences preferences = getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
//        String encryptedValue = AwbKeyGenerator.encryptString(context, key, String.valueOf(value));
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Clear all preferences.
     *
     * @param context
     */
    public static void clearPreferences(final Context context) {

        final SharedPreferences preferences = getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * Clear value for the key passed in preferences.
     *
     * @param context
     */
    public static void clearPreferences(final Context context, final String key) {

        final SharedPreferences preferences = getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }



}
