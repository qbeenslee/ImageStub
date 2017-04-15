package com.qbeenslee.imagestub.adapter.fresco;


import android.support.annotation.NonNull;

import com.qbeenslee.imagestub.interfaces.ImageLoaderFactory;
import com.qbeenslee.imagestub.interfaces.ImageLoaderProvider;


/**
 *
 */
public class FrescoFactory implements ImageLoaderFactory {
    public static final String TAG = "fresco";

    @NonNull
    @Override
    public ImageLoaderProvider newInstance() {
        return new FrescoProvider();
    }

    @Override
    public String toString() {
        return TAG;
    }
}
