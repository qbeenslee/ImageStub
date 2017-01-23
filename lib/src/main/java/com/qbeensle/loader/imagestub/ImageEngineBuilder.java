package com.qbeensle.loader.imagestub;

import android.support.annotation.DrawableRes;

import com.qbeensle.loader.imagestub.annotation.PictureScaleType;
import com.qbeensle.loader.imagestub.interfaces.ImageLoaderFactory;


/**
 * 构建器(配置与调用解离)
 */
public class ImageEngineBuilder {
    private final ImageConfig mConfig;
    private final ImageLoaderFactory mLoaderFactory;

    /*package*/ ImageEngineBuilder(ImageLoaderFactory factory) {
        mConfig = new ImageConfig();
        mLoaderFactory = factory;
    }

    public ImageEngineBuilder layout(int width, int height) {
        mConfig.width = width;
        mConfig.height = height;
        return this;
    }

    public ImageEngineBuilder scaleType(@PictureScaleType int scaleType) {
        mConfig.scaleType = scaleType;
        return this;
    }

    public ImageEngineBuilder placeholder(@DrawableRes int image) {
        mConfig.placeholderImage = image;
        return this;
    }

    public ImageEngineBuilder failure(@DrawableRes int image) {
        mConfig.failureImage = image;
        return this;
    }

    public ImageLoaderWrapper create() {
        return new ImageLoaderWrapper(mLoaderFactory.newInstance(), mConfig);
    }

}