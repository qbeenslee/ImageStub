package com.qbeenslee.imagestub.adapter.uil;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qbeenslee.imagestub.annotation.PictureScaleType;
import com.qbeenslee.imagestub.annotation.ScaleType;
import com.qbeenslee.imagestub.interfaces.ImageLoaderProvider;

/**
 *
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
                .showImageOnFail(failureImage) // resource or drawable
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(1000)
                .cacheInMemory(false) // default
                .cacheOnDisk(false) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .build();

        view.setScaleType(ScaleType.toImageViewScaleType(scaleType));
        ImageLoader.getInstance().displayImage(uri.toString(), view, imageOptions);
    }
}