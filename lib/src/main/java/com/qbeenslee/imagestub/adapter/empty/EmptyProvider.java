package com.qbeenslee.imagestub.adapter.empty;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.qbeenslee.imagestub.annotation.PictureScaleType;
import com.qbeenslee.imagestub.interfaces.ImageLoaderProvider;


/**
 * 空实现, 为了避免没有图片加载框架时crash
 */
final class EmptyProvider implements ImageLoaderProvider {
    @Override
    public ImageView createImageView(@NonNull Context context) {
        return new ImageView(context);
    }

    @Override
    public void paintImageView(@NonNull ImageView view, @NonNull Uri uri, int width, int height, @PictureScaleType int scaleType, @DrawableRes int placeholderImage, @DrawableRes int failureImage) {
        Log.i("IMAGE-STUB", "LOST IMAGE LOADER");
    }
}
