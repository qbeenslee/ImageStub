package com.qbeenslee.imagestub.adapter.picasso;


import android.support.annotation.NonNull;

import com.qbeenslee.imagestub.interfaces.ImageLoaderFactory;
import com.qbeenslee.imagestub.interfaces.ImageLoaderProvider;


/**
 * Picasso
 */
public class PicassoFactory implements ImageLoaderFactory {
    public static final String TAG = "picasso";

    @NonNull
    @Override
    public ImageLoaderProvider newInstance() {
        return new PicassoProvider();
    }

    @Override
    public String toString() {
        return TAG;
    }
}