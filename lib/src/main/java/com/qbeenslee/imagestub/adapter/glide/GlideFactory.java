package com.qbeenslee.imagestub.adapter.glide;

import android.support.annotation.NonNull;

import com.qbeenslee.imagestub.interfaces.ImageLoaderFactory;
import com.qbeenslee.imagestub.interfaces.ImageLoaderProvider;


/**
 *
 */
public class GlideFactory implements ImageLoaderFactory {
    public static final String TAG = "glide";

    @NonNull
    @Override
    public ImageLoaderProvider newInstance() {
        return new GlideProvider();
    }

    @Override
    public String toString() {
        return TAG;
    }
}
