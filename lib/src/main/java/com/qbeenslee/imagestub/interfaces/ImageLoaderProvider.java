package com.qbeenslee.imagestub.interfaces;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.qbeenslee.imagestub.annotation.PictureScaleType;


/**
 * 图片
 */
@Keep
public interface ImageLoaderProvider {

    /**
     * 生成图片视图容器.
     * (接口设计原因: 有些图片加载框架要依赖于视图)
     *
     * @param context 上下文
     * @return View
     */
    ImageView createImageView(@NonNull Context context);

    /**
     * 开始绘制(请求网络图片)视图.
     *
     * @param view             视图
     * @param uri              链接
     * @param scaleType        缩放模式
     * @param placeholderImage 占位图
     * @param width            容器宽度, 单位px
     * @param height           容器高度, 单位px
     * @param failureImage     失败图
     */
    void paintImageView(@NonNull ImageView view,
                        @NonNull Uri uri,
                        int width, int height,
                        @PictureScaleType int scaleType,
                        @DrawableRes int placeholderImage,
                        @DrawableRes int failureImage
    );

}
