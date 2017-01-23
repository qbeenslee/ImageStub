package com.qbeenslee.loader.imagestub.provider;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qbeensle.loader.imagestub.annotation.PictureScaleType;
import com.qbeensle.loader.imagestub.interfaces.ImageLoaderProvider;

/**
 */
final class UILProvider implements ImageLoaderProvider {

    @Override
    public ImageView createImageView(@NonNull Context context) {
        return new ImageView(context);
    }

    @Override
    public void paintImageView(@NonNull ImageView view, @NonNull Uri uri, int width, int height, @PictureScaleType int scaleType, @DrawableRes int placeholderImage, @DrawableRes int failureImage) {
            DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(placeholderImage) // resource or drawable
                    .showImageOnFail(placeholderImage) // resource or drawable
                    .resetViewBeforeLoading(false)  // default
                    .delayBeforeLoading(1000)
                    .cacheInMemory(false) // default
                    .cacheOnDisk(false) // default
                    //.imageScaleType(PictureScaleType.IN_SAMPLE_POWER_OF_2) // default
                    .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                    .build();

        ImageLoader.getInstance().displayImage(uri.toString(), view, imageOptions);
    }
}
