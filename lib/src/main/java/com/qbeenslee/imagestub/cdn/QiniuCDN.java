package com.qbeenslee.imagestub.cdn;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.qbeenslee.imagestub.annotation.Format;
import com.qbeenslee.imagestub.annotation.PictureFormat;
import com.qbeenslee.imagestub.annotation.PictureScaleType;
import com.qbeenslee.imagestub.annotation.ScaleType;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 根据七牛CDN可配置化参数优化下载图片大小
 * <pre>
 *     imageView2/{mode}/w/{LongEdge}
 *                      /h/{ShortEdge}
 *                      /format/{Format}
 *                      /interlace/{Interlace}
 *                      /q/{Quality}
 * </pre>
 */
final class QiniuCDN {
    private static final Set<String> SUPPORT_HOSTS = new LinkedHashSet<>(Arrays.asList(
            "clouddn.com"
    ));

    /**
     * 限定尺寸的阈值设为480px, 是考虑高于这个分辨率后的屏幕上查看图片非全分辨率的视觉损失很小.
     * 而当图片尺寸过小去请求被缩小的资源在对比服务端处理消耗时间和网络传输消耗的时间不是一个很有价值的操作.
     *
     * @param length 尺寸
     */
    private static int encodeLength(int length) {
        int result = length;
        if (result > 480) {
            result = (int) (length * 0.75);
        }
        return result;
    }

    /**
     * ScaleType.FIT_XY, ScaleType.FIT_START, ScaleType.FIT_CENTER,
     * ScaleType.FIT_END, ScaleType.CENTER, ScaleType.CENTER_INSIDE,
     * ScaleType.CENTER_CROP, ScaleType.FOCUS_CROP
     */
    private static int parseMode(@PictureFormat int format, @PictureScaleType int scaleType, int width, int height) {
        int mode = 2;
        switch (scaleType) {
            case ScaleType.FIT_XY:
            case ScaleType.FIT_START:
            case ScaleType.FIT_CENTER:
            case ScaleType.FIT_END:
            case ScaleType.CENTER_INSIDE:
            case ScaleType.CENTER_CROP:
                if (width == height && width != 0 && format != Format.FORMAT_PNG) {
                    mode = 1;
                }
                break;
            case ScaleType.CENTER:
            case ScaleType.FOCUS_CROP:
                if (format != Format.FORMAT_PNG) {
                    mode = 1;
                }
                break;
            default:
                break;
        }
        return mode;
    }

    private static String getFileSuffix(String path) {
        String[] parts = path.split("\\.");
        return parts[parts.length - 1];
    }


    /**
     * 是否支持该链接
     *
     * @param uri 资源定位符
     * @return True 支持.
     */
    public static boolean isSupport(Uri uri) {
        String url = uri.toString();
        if (!TextUtils.isEmpty(url) && url.contains("!")) {
            return false;
        }
        final String host = uri.getHost();
        if (!TextUtils.isEmpty(host)) {
            for (String value : SUPPORT_HOSTS) {
                if (host.contains(value)) {
                    return true;
                }
            }
        }
        return false;
    }

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
        String imageFormat = Format.toString(format);
        @PictureFormat int originalFormat = Format.suffixMatch(getFileSuffix(uri.getLastPathSegment()));
        final int imageMode = parseMode(originalFormat, scaleType, width, height);

        //api
        StringBuilder pBuilder = new StringBuilder("imageView2");//Parameter builder
        //mode
        pBuilder.append('/');
        pBuilder.append(imageMode);
        //size
        if (width > 0) {
            pBuilder.append("/w/");
            pBuilder.append(encodeLength(width));
        }
        if (height > 0) {
            pBuilder.append("/h/");
            pBuilder.append(encodeLength(height));
        }
        //format
        pBuilder.append("/format/");
        if (originalFormat == Format.FORMAT_PNG || originalFormat == Format.FORMAT_GIF) {
            pBuilder.append(Format.toString(originalFormat));
        } else {
            pBuilder.append(imageFormat);
        }

        if (interlace) {
            pBuilder.append("/interlace/1");
        }
        pBuilder.append("/q/");
        pBuilder.append(quality % 101);

        return uri.buildUpon().encodedQuery(pBuilder.toString()).build();
    }
}
