package com.qbeenslee.loader.imagestub.provider;


import com.qbeensle.loader.imagestub.interfaces.ImageLoaderFactory;
import com.qbeensle.loader.imagestub.interfaces.ImageLoaderProvider;

/**
 */
public class FrescoFactory implements ImageLoaderFactory {

    @Override
    public ImageLoaderProvider newInstance() {
        return new FrescoProvider();
    }
}
