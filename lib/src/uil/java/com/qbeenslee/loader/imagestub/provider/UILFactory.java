package com.qbeenslee.loader.imagestub.provider;


import android.support.annotation.NonNull;

import com.qbeensle.loader.imagestub.interfaces.ImageLoaderFactory;
import com.qbeensle.loader.imagestub.interfaces.ImageLoaderProvider;

/**
 * UIL
 */
public class UILFactory implements ImageLoaderFactory {
    @NonNull
    @Override
    public ImageLoaderProvider newInstance() {
        return new UILProvider();
    }
}
