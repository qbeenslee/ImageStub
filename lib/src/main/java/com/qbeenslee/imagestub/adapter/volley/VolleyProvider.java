package com.qbeenslee.imagestub.adapter.volley;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.qbeenslee.imagestub.annotation.PictureScaleType;
import com.qbeenslee.imagestub.annotation.ScaleType;
import com.qbeenslee.imagestub.interfaces.ImageLoaderProvider;

/**
 */
final class VolleyProvider implements ImageLoaderProvider {

    @Override
    public ImageView createImageView(@NonNull Context context) {
        return new NetworkImageView(context);
    }

    @Override
    public void paintImageView(@NonNull ImageView view, @NonNull Uri uri, int width, int height, @PictureScaleType int scaleType, @DrawableRes int placeholderImage, @DrawableRes int failureImage) {
        if (view instanceof NetworkImageView) {
            NetworkImageView imageView = (NetworkImageView) view;
            if (placeholderImage != 0) {
                imageView.setDefaultImageResId(placeholderImage);
            }
            if (failureImage != 0) {
                imageView.setErrorImageResId(failureImage);
            }
            imageView.setScaleType(ScaleType.toImageViewScaleType(scaleType));
            ImageLoader mImageLoader = new ImageLoader(Volley.newRequestQueue(view.getContext()), new BitmapCache());
            imageView.setImageUrl(uri.toString(), mImageLoader);
        }
    }
}
