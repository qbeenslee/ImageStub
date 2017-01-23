package com.qbeensle.loader.imagestub.cdn;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.qbeensle.loader.imagestub.annotation.PictureFormat;
import com.qbeensle.loader.imagestub.annotation.PictureScaleType;


/**
 * 统调CDN链接
 */

public class CdnUrl {

    /**
     * 进行转换
     *
     * @param uri       资源定位符
     * @param scaleType 缩放类型
     * @param width     容器宽
     * @param height    容器高
     * @param format    图片格式
     * @param interlace 是否支持交错加载
     * @param quality   质量
     * @return 处理过的资源定位符.
     */
    public static Uri transform(@NonNull Uri uri, @PictureScaleType int scaleType, int width, int height, @PictureFormat int format, boolean interlace, int quality) {
        if (QiniuCDN.isSupport(uri)) {
            return QiniuCDN.transform(uri, scaleType, width, height, format, interlace, quality);
        } else if (WechatCDN.isSupport(uri)) {
            return WechatCDN.transform(uri, scaleType, width, height, format, interlace, quality);
        } else {
            return uri;
        }
    }
}
