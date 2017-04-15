package com.qbeenslee.imagestub;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.qbeenslee.imagestub.annotation.Format;
import com.qbeenslee.imagestub.cdn.CdnUrl;
import com.qbeenslee.imagestub.interfaces.ImageLoaderProvider;


/**
 * ImageView装饰器
 */
public class ImageLoaderWrapper {
    @NonNull
    private final ImageConfig mConfig;
    private final ImageLoaderProvider mProvider;


    ImageLoaderWrapper(ImageLoaderProvider provider, @NonNull ImageConfig config) {
        mProvider = provider;
        mConfig = config;
    }

    public ImageView createView(@NonNull Context context) {
        return mProvider.createImageView(context);
    }

    public void load(ImageView view, @NonNull Uri uri) {
        uri = CdnUrl.transform(uri, mConfig.scaleType, mConfig.width, mConfig.height, Format.FORMAT_JPEG, true, mConfig.quality);
        mProvider.paintImageView(view, uri, mConfig.width, mConfig.height, mConfig.scaleType, mConfig.placeholderImage, mConfig.failureImage);
    }
}
