package com.qbeensle.loader.imagestub;

import android.support.annotation.DrawableRes;

import com.qbeensle.loader.imagestub.annotation.PictureScaleType;
import com.qbeensle.loader.imagestub.annotation.ScaleType;


/**
 * 图片加载器配置
 */
final class ImageConfig {
    /**
     * 布局宽度
     */
    int width = 0;
    /**
     * 布局高度
     */
    int height = 0;
    /**
     * 图片缩放模式
     */
    @PictureScaleType
    int scaleType = ScaleType.CENTER_CROP;
    /**
     * 占位图片
     */
    @DrawableRes
    int placeholderImage = 0;
    /**
     * 加载失败图片
     */
    @DrawableRes
    int failureImage = 0;
}
