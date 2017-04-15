package com.qbeenslee.imagestub.adapter.empty;

import android.support.annotation.NonNull;

import com.qbeenslee.imagestub.interfaces.ImageLoaderFactory;
import com.qbeenslee.imagestub.interfaces.ImageLoaderProvider;


/**
 * 虚空工厂
 */
public class EmptyFactory implements ImageLoaderFactory {
    public static final String TAG = "";

    @NonNull
    @Override
    public ImageLoaderProvider newInstance() {
        return new EmptyProvider();
    }

    @Override
    public String toString() {
        return TAG;
    }
}
