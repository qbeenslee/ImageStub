package com.qbeensle.loader.imagestub;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.qbeensle.loader.imagestub.annotation.Format;
import com.qbeensle.loader.imagestub.annotation.PictureScaleType;
import com.qbeensle.loader.imagestub.cdn.CdnUrl;
import com.qbeensle.loader.imagestub.interfaces.ImageLoaderProvider;


/**
 * ImageView装饰器
 */
public class ImageLoaderWrapper {
    private static final int RESOURCE_QUALITY = 85;
    private final ImageLoaderProvider mProvider;
    private final ImageConfig mConfig;


    ImageLoaderWrapper(ImageLoaderProvider provider, ImageConfig config) {
        mProvider = provider;
        mConfig = config;
    }

    public ImageView createView(@NonNull Context context) {
        return mProvider.createImageView(context);
    }

    public void load(ImageView view, @NonNull Uri uri) {
        paintImageView(view, uri, mConfig.width, mConfig.height, mConfig.scaleType, mConfig.placeholderImage, mConfig.failureImage);
    }

    private void paintImageView(@NonNull ImageView view,
                                @NonNull Uri uri,
                                int width, int height,
                                @PictureScaleType int scaleType,
                                @DrawableRes int placeholderImage,
                                @DrawableRes int failureImage) {
        uri = CdnUrl.transform(uri, scaleType, width, height, Format.FORMAT_JPEG, true, RESOURCE_QUALITY);
        mProvider.paintImageView(view, uri, width, height, scaleType, placeholderImage, failureImage);
    }
}
