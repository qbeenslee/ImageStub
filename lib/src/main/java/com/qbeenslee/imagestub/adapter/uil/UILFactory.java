package com.qbeenslee.imagestub.adapter.uil;


import android.support.annotation.NonNull;

import com.qbeenslee.imagestub.interfaces.ImageLoaderFactory;
import com.qbeenslee.imagestub.interfaces.ImageLoaderProvider;


/**
 * UIL
 */
public class UILFactory implements ImageLoaderFactory {
    public static final String TAG = "uil";

    @NonNull
    @Override
    public ImageLoaderProvider newInstance() {
        return new UILProvider();
    }

    @Override
    public String toString() {
        return TAG;
    }
}
