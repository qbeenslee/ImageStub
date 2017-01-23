package com.qbeensle.loader.imagestub.annotation;

import android.content.res.TypedArray;
import android.widget.ImageView;

/**
 * Options for scaling the child bounds to the parent bounds.
 * Note: The enum values should be in sync with the scaleType attribute values in attrs.xml
 */
public class ScaleType {

    /**
     * Scales width and height independently, so that the child matches the parent exactly.
     * This may change the aspect ratio of the child.
     */
    public static final int FIT_XY = 0;

    /**
     * Scales the child so that it fits entirely inside the parent. At least one dimension (width or
     * height) will fit exactly. Aspect ratio is preserved.
     * Child is aligned to the top-left corner of the parent.
     */
    public static final int FIT_START = 1;

    /**
     * Scales the child so that it fits entirely inside the parent. At least one dimension (width or
     * height) will fit exactly. Aspect ratio is preserved.
     * Child is centered within the parent's bounds.
     */
    public static final int FIT_CENTER = 2;

    /**
     * Scales the child so that it fits entirely inside the parent. At least one dimension (width or
     * height) will fit exactly. Aspect ratio is preserved.
     * Child is aligned to the bottom-right corner of the parent.
     */
    public static final int FIT_END = 3;

    /**
     * Performs no scaling.
     * Child is centered within parent's bounds.
     */
    public static final int CENTER = 4;

    /**
     * Scales the child so that it fits entirely inside the parent. Unlike FIT_CENTER, if the child
     * is smaller, no up-scaling will be performed. Aspect ratio is preserved.
     * Child is centered within parent's bounds.
     */
    public static final int CENTER_INSIDE = 5;

    /**
     * Scales the child so that both dimensions will be greater than or equal to the corresponding
     * dimension of the parent. At least one dimension (width or height) will fit exactly.
     * Child is centered within parent's bounds.
     */
    public static final int CENTER_CROP = 6;

    /**
     * Scales the child so that both dimensions will be greater than or equal to the corresponding
     * dimension of the parent. At least one dimension (width or height) will fit exactly.
     * The child's focus point will be centered within the parent's bounds as much as possible
     * without leaving empty space.
     * It is guaranteed that the focus point will be visible and centered as much as possible.
     * If the focus point is set to (0.5f, 0.5f), result will be equivalent to CENTER_CROP.
     */
    public static final int FOCUS_CROP = 7;


    /**
     * Returns the scale type indicated in XML, or null if the special 'none' value was found.
     */
    @PictureScaleType
    public static int fromXmlScaleType(TypedArray attrs, int attrId, @PictureScaleType int
            defaultScaleType) {
        final @PictureScaleType int index = attrs.getInt(attrId, -1);
        return index < 0 ? defaultScaleType : index;
    }

    public static ImageView.ScaleType toImageViewScaleType(int scaleType) {
        switch (scaleType) {
            case FIT_XY:
                return ImageView.ScaleType.FIT_XY;
            case FIT_START:
                return ImageView.ScaleType.FIT_START;
            case FIT_CENTER:
                return ImageView.ScaleType.FIT_CENTER;
            case FIT_END:
                return ImageView.ScaleType.FIT_END;
            case CENTER:
                return ImageView.ScaleType.CENTER;
            case CENTER_INSIDE:
                return ImageView.ScaleType.CENTER_INSIDE;
            case CENTER_CROP:
                return ImageView.ScaleType.CENTER_CROP;
            case FOCUS_CROP:
                return ImageView.ScaleType.CENTER_CROP;
            default:
                return ImageView.ScaleType.FIT_CENTER;
        }
    }

    @PictureScaleType
    public static int fromImageViewScaleType(int index) {
        switch (index) {
            case 1:
                return FIT_XY;
            case 2:
                return FIT_START;
            case 3:
                return FIT_CENTER;
            case 4:
                return FIT_END;
            case 5:
                return CENTER;
            case 6:
                return CENTER_CROP;
            case 7:
                return CENTER_INSIDE;
            default:
                return FIT_CENTER;
        }
    }

}
