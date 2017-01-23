package com.qbeenslee.loader.imagestub.provider;


import android.support.annotation.NonNull;

import com.qbeensle.loader.imagestub.interfaces.ImageLoaderFactory;
import com.qbeensle.loader.imagestub.interfaces.ImageLoaderProvider;


/**
 * Picasso
 */
public class PicassoFactory implements ImageLoaderFactory {

    @NonNull
    @Override
    public ImageLoaderProvider newInstance() {
        return new PicassoProvider();
    }
}
