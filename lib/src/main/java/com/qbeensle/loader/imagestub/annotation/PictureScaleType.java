package com.qbeensle.loader.imagestub.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 图片ScaleType
 */
@IntDef({ScaleType.FIT_XY, ScaleType.FIT_START, ScaleType.FIT_CENTER,
        ScaleType.FIT_END, ScaleType.CENTER, ScaleType.CENTER_INSIDE,
        ScaleType.CENTER_CROP, ScaleType.FOCUS_CROP})
@Retention(RetentionPolicy.SOURCE)
public @interface PictureScaleType {
}