package com.qbeenslee.imagestub.adapter.picasso;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.qbeenslee.imagestub.annotation.PictureScaleType;
import com.qbeenslee.imagestub.annotation.ScaleType;
import com.qbeenslee.imagestub.interfaces.ImageLoaderProvider;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 *
 */
final class PicassoProvider implements ImageLoaderProvider {

    @Override
    public ImageView createImageView(@NonNull Context context) {
        return new ImageView(context);
    }

    @Override
    public void paintImageView(@NonNull ImageView view, @NonNull Uri uri, int width, int height, @PictureScaleType int scaleType, @DrawableRes int placeholderImage, @DrawableRes int failureImage) {

        RequestCreator requestCreator = Picasso.with(view.getContext()).load(uri);

        if (width > 0 && height > 0) {
            requestCreator.resize(width, height);
        }
        if (placeholderImage != 0) {
            requestCreator.placeholder(placeholderImage);

        }
        if (failureImage != 0) {
            requestCreator.error(failureImage);
        }

        switch (scaleType) {
            case ScaleType.FIT_XY:
            case ScaleType.FIT_START:
            case ScaleType.FIT_CENTER:
            case ScaleType.FIT_END:
            case ScaleType.CENTER:
            case ScaleType.CENTER_INSIDE:
                requestCreator.centerInside();
                break;
            case ScaleType.CENTER_CROP:
                requestCreator.centerCrop();
                break;
            case ScaleType.FOCUS_CROP:
            default:
                break;
        }

        requestCreator.into(view);
    }
}
