package com.qbeenslee.imagestub.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.qbeenslee.imagestub.adapter.empty.EmptyFactory;
import com.qbeenslee.imagestub.adapter.fresco.FrescoFactory;
import com.qbeenslee.imagestub.adapter.glide.GlideFactory;
import com.qbeenslee.imagestub.adapter.picasso.PicassoFactory;
import com.qbeenslee.imagestub.adapter.uil.UILFactory;
import com.qbeenslee.imagestub.adapter.volley.VolleyFactory;
import com.qbeenslee.imagestub.interfaces.ImageLoaderFactory;

/**
 * Sniffer for image loader in project
 */
public class Sniffer {

    @Nullable
    private static Class<? extends ImageLoaderFactory> sniffer() {
        if (findClass("com.squareup.picasso.Picasso")) {
            return PicassoFactory.class;
        } else if (findClass("com.android.volley.toolbox.Volley")) {
            return VolleyFactory.class;
        } else if (findClass("com.bumptech.glide.Glide")) {
            return GlideFactory.class;
        } else if (findClass("com.nostra13.universalimageloader.core.ImageLoader")) {
            return UILFactory.class;
        } else if (findClass("com.facebook.drawee.backends.pipeline.Fresco")) {
            return FrescoFactory.class;
        } else {
            return EmptyFactory.class;
        }
    }

    private static Class<? extends ImageLoaderFactory> findFactoryByTag(String tag) {
        if (PicassoFactory.TAG.equals(tag)) {
            return findClass("com.squareup.picasso.Picasso") ? PicassoFactory.class : null;
        } else if (VolleyFactory.TAG.equals(tag)) {
            return findClass("com.android.volley.toolbox.Volley") ? VolleyFactory.class : null;
        } else if (GlideFactory.TAG.equals(tag)) {
            return findClass("com.bumptech.glide.Glide") ? GlideFactory.class : null;
        } else if (UILFactory.TAG.equals(tag)) {
            return findClass("com.nostra13.universalimageloader.core.ImageLoader") ? UILFactory.class : null;
        } else if (FrescoFactory.TAG.equals(tag)) {
            return findClass("com.facebook.drawee.backends.pipeline.Fresco") ? FrescoFactory.class : null;
        } else {
            return null;
        }
    }

    public static ImageLoaderFactory getImageLoaderFactory() {
        Class<? extends ImageLoaderFactory> clz = findByConfig();
        if (clz == null) {
            clz = sniffer();
        }
        if (clz != null) {
            try {
                Object obj = clz.newInstance();
                if (obj instanceof ImageLoaderFactory) {
                    Preference.Config.setImageLoadAdapter(obj.toString());
                    return (ImageLoaderFactory) obj;
                }
            } catch (Throwable ignored) {
            }
        }
        return null;
    }

    private static boolean findClass(@NonNull String name) {
        try {
            Class.forName(name);
            return true;
        } catch (Throwable ignored) {
            //Be sure use throwable to catch!
        }
        return false;
    }

    @Nullable
    private static Class<? extends ImageLoaderFactory> findByConfig() {
        final String name = Preference.Config.getImageLoadAdapter();
        return !TextUtils.isEmpty(name) ? findFactoryByTag(name) : null;
    }

}
