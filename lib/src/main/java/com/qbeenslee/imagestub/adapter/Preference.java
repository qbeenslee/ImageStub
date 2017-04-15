package com.qbeenslee.imagestub.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 配置项目
 */
public final class Preference {
    private static final String SIGN_SP_NAME = "com.qbeenslee.loader.image.imagestub";
    private static final String VALUE_INVALID = "!@INVALID!@";
    private static final String SP_KEY_CONFIG_IMAGE_LOAD_ADAPTER = "config.image_loader";
    private static Preference instance;
    private final Bundle mCache;
    @Nullable
    private SharedPreferences mSP;
    @Nullable
    private SharedPreferences.Editor mEditor;

    private Preference() {
        mCache = new Bundle();
    }

    public static Preference instance() {
        if (instance == null) {
            synchronized (Preference.class) {
                if (instance == null) {
                    instance = new Preference();
                }
            }
        }
        return instance;
    }

    public static void renew(Context context) {
        if (!instance().checked() && context != null) {
            instance().init(context);
        }
    }

    private boolean checked() {
        return mSP != null && mEditor != null;
    }

    public void init(Context context) {
        Context appCtx = context.getApplicationContext();
        if (appCtx != null) {
            context = appCtx;
        }
        mSP = context.getSharedPreferences(SIGN_SP_NAME, Context.MODE_PRIVATE);
        mEditor = mSP.edit();
    }

    private void remove(String key) {
        mCache.remove(key);
        if (checked()) {
            mEditor.remove(key).commit();
        }
    }

    private String getString(String key, String defaultValue) {
        String value = mCache.getString(key, VALUE_INVALID);
        if (!VALUE_INVALID.equals(value)) {
            return value;
        } else {
            if (checked()) {
                value = mSP.getString(key, defaultValue);
                mCache.putString(key, value);
            } else {
                value = null;
            }
            return value;
        }
    }

    private void setString(String key, String value) {
        if (value == null) {
            remove(key);
        } else {
            if (checked()) {
                mEditor.putString(key, value);
                mEditor.commit();
            }
            mCache.putString(key, value);
        }
    }

    private int getInt(String key, int defaultValue) {
        int value = mCache.getInt(key, Integer.MIN_VALUE);
        if (value != Integer.MIN_VALUE) {
            return value;
        } else {
            if (checked()) {
                value = mSP.getInt(key, defaultValue);
                mCache.putInt(key, value);
            } else {
                value = 0;
            }
            return value;
        }
    }

    private void setInt(String key, int value) {
        if (value == Integer.MIN_VALUE) {
            remove(key);
        } else {
            if (checked()) {
                mEditor.putInt(key, value);
                mEditor.commit();
            }
            mCache.putInt(key, value);
        }
    }

    private long getLong(String key, long defaultValue) {
        long value = mCache.getLong(key, Long.MIN_VALUE);
        if (value != Long.MIN_VALUE) {
            return value;
        } else {
            if (checked()) {
                value = mSP.getLong(key, defaultValue);
                mCache.putLong(key, value);
            } else {
                value = 0;
            }
            return value;
        }
    }

    private void setLong(String key, long value) {
        if (value == Long.MIN_VALUE) {
            remove(key);
        } else {
            if (checked()) {
                mEditor.putLong(key, value);
                mEditor.commit();
            }
            mCache.putLong(key, value);
        }
    }

    /**
     * 配置
     */
    public static class Config {

        public static String getImageLoadAdapter() {
            return instance().getString(SP_KEY_CONFIG_IMAGE_LOAD_ADAPTER, null);
        }

        public static void setImageLoadAdapter(String value) {
            instance().setString(SP_KEY_CONFIG_IMAGE_LOAD_ADAPTER, value);
        }

        public static void clear(Context context) {
            Preference.renew(context);
            setImageLoadAdapter(null);
        }

    }
}
