package com.qbeensle.loader.imagestub.interfaces;

import android.support.annotation.NonNull;

/**
 * 工厂
 */
public interface ImageLoaderFactory {

    @NonNull
    ImageLoaderProvider newInstance();

}
