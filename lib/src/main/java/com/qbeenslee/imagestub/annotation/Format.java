package com.qbeenslee.imagestub.annotation;

/**
 * 图片格式
 */
public class Format {
    public static final int FORMAT_JPEG = 0x01;
    public static final int FORMAT_PNG = 0x02;
    public static final int FORMAT_GIF = 0x03;
    public static final int FORMAT_WEBP = 0x04;
    public static final int FORMAT_TIFF = 0x05;
    public static final int FORMAT_BMP = 0x06;


    public static String toString(@PictureFormat int format) {
        switch (format) {
            case Format.FORMAT_JPEG:
                return "jpeg";
            case Format.FORMAT_PNG:
                return "png";
            case Format.FORMAT_GIF:
                return "gif";
            case Format.FORMAT_WEBP:
                return "webp";
            case Format.FORMAT_TIFF:
                return "tiff";
            case Format.FORMAT_BMP:
                return "bmp";
            default:
                return "jpeg";
        }
    }

    public static String getSuffix(@PictureFormat int format) {
        switch (format) {
            case Format.FORMAT_JPEG:
                return "jpg";
            case Format.FORMAT_PNG:
                return "png";
            case Format.FORMAT_GIF:
                return "gif";
            case Format.FORMAT_WEBP:
                return "webp";
            case Format.FORMAT_TIFF:
                return "tiff";
            case Format.FORMAT_BMP:
                return "bmp";
            default:
                return "jpg";
        }
    }

    @PictureFormat
    public static int suffixMatch(String suffix) {
        if ("jpg".equalsIgnoreCase(suffix)) {
            return Format.FORMAT_JPEG;
        } else if ("png".equalsIgnoreCase(suffix)) {
            return Format.FORMAT_PNG;
        } else if ("gif".equalsIgnoreCase(suffix)) {
            return Format.FORMAT_GIF;
        } else if ("webp".equalsIgnoreCase(suffix)) {
            return Format.FORMAT_WEBP;
        } else if ("tiff".equalsIgnoreCase(suffix)) {
            return Format.FORMAT_TIFF;
        } else if ("bmp".equalsIgnoreCase(suffix)) {
            return Format.FORMAT_BMP;
        } else {
            return Format.FORMAT_JPEG;
        }
    }

}
