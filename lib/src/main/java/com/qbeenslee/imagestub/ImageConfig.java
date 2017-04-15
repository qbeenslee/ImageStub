package com.qbeenslee.imagestub;

import android.support.annotation.DrawableRes;

import com.qbeenslee.imagestub.annotation.PictureScaleType;
import com.qbeenslee.imagestub.annotation.ScaleType;


/**
 * 图片加载器配置
 */
final class ImageConfig {
    private final int RESOURCE_QUALITY = 85;
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

    /**
     * 图片质量
     * 默认为85%
     */
    int quality = RESOURCE_QUALITY;
}
