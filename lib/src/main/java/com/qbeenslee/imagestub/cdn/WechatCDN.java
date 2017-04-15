package com.qbeenslee.imagestub.cdn;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.qbeenslee.imagestub.annotation.PictureFormat;
import com.qbeenslee.imagestub.annotation.PictureScaleType;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据微信头像参数优化配置.
 * <a href="https://mp.weixin.qq.com/wiki/1/8a5ce6257f1d3b2afb20f83e72b72ce9.html">文档地址</a>
 * <img alt="示例图片" src="http://wx.qlogo.cn/mmopen/sc8lJYpicUaXIF9cicaDhicH6t4xFhBFJdaWgkGkiaSiaH8IaU09YWyIF0CGOese07QKpzUiaqzgq51800Agiakm4UzcLiawO5vicicIXia/0"/>
 * <pre>
 *     用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。
 *     若用户更换头像，原有头像URL将失效
 * </pre>
 */

final class WechatCDN {
    private static final String SUPPORT_HOST = "wx.qlogo.cn";

    private static int findBestSize(int size) {
        if (size == 0) {
            return 0;
        } else if (0 < size && size <= 46) {
            return 46;
        } else if (46 < size && size <= 64) {
            return 64;
        } else if (64 < size && size <= 96) {
            return 96;
        } else if (96 < size && size <= 175) {
            return 132;//132到640的跳幅过大.
        } else {
            return 0;
        }
    }

    private static boolean isNumeric(String number) {
        if (number != null && number.length() > 0) {
            final int length = number.length();
            for (int i = 0; i < length; ++i) {
                final char ch = number.charAt(i);
                if (ch > '9' || ch < '0') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 是否支持该链接
     *
     * @param uri 资源定位符
     * @return True 支持.
     */
    public static boolean isSupport(Uri uri) {
        return SUPPORT_HOST.equalsIgnoreCase(uri.getHost());
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
        List<String> path = ensureUriPath(uri);
        if (path != null && path.size() > 0) {
            Uri.Builder builder = uri.buildUpon();
            builder.path(null);
            for (String item : path) {
                builder.appendPath(item);
            }
            final int mode = findBestSize(Math.max(width, height));
            builder.appendPath(String.valueOf(mode));
            return builder.build();
        }
        return uri;
    }

    private static List<String> ensureUriPath(Uri uri) {
        List<String> path = uri.getPathSegments();
        if (path != null && path.size() > 0) {
            final int pathCount = path.size();
            final String originalMode = path.get(pathCount - 1);
            if (isNumeric(originalMode)) {
                final int mode = Integer.valueOf(originalMode);
                final int appendPathLength = mode == findBestSize(mode) ? pathCount - 1 : pathCount;
                List<String> ensurePath = new ArrayList<>(appendPathLength);
                for (int i = 0; i < appendPathLength; ++i) {
                    ensurePath.add(path.get(i));
                }
                return ensurePath;
            }
        }
        return null;
    }

}
