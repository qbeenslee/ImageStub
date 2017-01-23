package com.qbeensle.loader.imagestub;


import com.qbeensle.loader.imagestub.interfaces.ImageLoaderFactory;

/**
 * 图片加载调用封装
 */
public final class ImageEngine {
    private ImageLoaderFactory mLoaderFactory;

    public ImageEngine() {
    }

    public static ImageEngine instance() {
        return ClassHolder.instance;
    }

    public void setFactory(ImageLoaderFactory factory) {
        mLoaderFactory = factory;
    }

    public ImageEngineBuilder build() {
        return new ImageEngineBuilder(mLoaderFactory);
    }

    private static class ClassHolder {
        private final static ImageEngine instance = new ImageEngine();
    }
}
