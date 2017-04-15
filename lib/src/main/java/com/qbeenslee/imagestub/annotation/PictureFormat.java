package com.qbeenslee.imagestub.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 图片格式注解
 */
@IntDef({Format.FORMAT_JPEG,
        Format.FORMAT_PNG, Format.FORMAT_GIF,
        Format.FORMAT_BMP, Format.FORMAT_TIFF,
        Format.FORMAT_WEBP})
@Retention(RetentionPolicy.SOURCE)
public @interface PictureFormat {
}
