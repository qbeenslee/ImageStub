package com.qbeenslee.imagestub.adapter.volley;


import android.support.annotation.NonNull;

import com.qbeenslee.imagestub.interfaces.ImageLoaderFactory;
import com.qbeenslee.imagestub.interfaces.ImageLoaderProvider;


/**
 * Volley
 */
public class VolleyFactory implements ImageLoaderFactory {
    public static final String TAG = "volley";

    @NonNull
    @Override
    public ImageLoaderProvider newInstance() {
        return new VolleyProvider();
    }

    @Override
    public String toString() {
        return TAG;
    }
}