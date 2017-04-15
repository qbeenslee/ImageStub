package com.qbeenslee.imagestub.adapter.glide;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.qbeenslee.imagestub.annotation.PictureScaleType;
import com.qbeenslee.imagestub.annotation.ScaleType;
import com.qbeenslee.imagestub.interfaces.ImageLoaderProvider;

/**
 *
 */
final class GlideProvider implements ImageLoaderProvider {


    @Override
    public ImageView createImageView(@NonNull Context context) {
        return new ImageView(context);
    }

    @Override
    public void paintImageView(@NonNull ImageView view, @NonNull Uri uri, int width, int height, @PictureScaleType int scaleType, @DrawableRes int placeholderImage, @DrawableRes int failureImage) {
        DrawableTypeRequest<Uri> request = Glide.with(view.getContext()).load(uri);
        if (placeholderImage != 0) {
            request.placeholder(placeholderImage);
        }
        if (failureImage != 0) {
            request.error(failureImage);
        }
        request.override(width, height);
        switch (scaleType) {
            case ScaleType.FIT_XY:
            case ScaleType.FIT_START:
            case ScaleType.FIT_CENTER:
                request.fitCenter();
                break;
            case ScaleType.FIT_END:
            case ScaleType.CENTER:
            case ScaleType.CENTER_INSIDE:
            case ScaleType.CENTER_CROP:
                request.centerCrop();
                break;
            case ScaleType.FOCUS_CROP:
            default:
                break;
        }

        request.into(view);
    }
}
