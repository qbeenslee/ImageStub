package com.qbeenslee.imagestub;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.qbeenslee.imagestub.adapter.Sniffer;
import com.qbeenslee.imagestub.annotation.PictureScaleType;
import com.qbeenslee.imagestub.annotation.ScaleType;
import com.qbeenslee.imagestub.interfaces.ImageLoaderProvider;


/**
 * ImageView placeholder layout
 */
public class ImageStub extends ViewGroup {
    private static final int[] ANDROID_ATTRS = new int[]{
            android.R.attr.layout_width,
            android.R.attr.layout_height,
            android.R.attr.background
    };
    @PictureScaleType
    private int mScaleType = ScaleType.CENTER_CROP;
    private ImageView mHolder;
    private ImageLoaderWrapper mLoaderWrapper;
    private int mLayoutWidth = 0;
    private int mLayoutHeight = 0;
    private int mBackgroundRef = 0;
    private int mFailureRef = 0;
    private String mImageUri;

    public ImageStub(Context context) {
        super(context, null);
        init(context);
    }

    public ImageStub(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) return;
        if (attrs != null) {
            TypedArray sysTyped = context.obtainStyledAttributes(attrs, ANDROID_ATTRS);
            mLayoutWidth = (int) sysTyped.getDimension(0, mLayoutWidth);
            mLayoutHeight = (int) sysTyped.getDimension(1, mLayoutHeight);
            mBackgroundRef = sysTyped.getInt(2, mBackgroundRef);
            sysTyped.recycle();
        }
        init(context);
    }

    private void init(Context context) {
        if (ImageEngine.instance().getFactory() == null) {
            ImageEngine.instance().setFactory(Sniffer.getImageLoaderFactory());
        }
        //setVisibility(GONE);
        setWillNotDraw(true);
    }

    @Override
    public void setId(int id) {
        super.setId(id);
        //mLayoutId = id;
    }

    private void generateLoaderWrapper(int width, int height) {
        if (mLoaderWrapper != null) return;
        if (width > 0 && height > 0) {
            mLoaderWrapper = ImageEngine.instance()
                    .build()
                    .layout(width, height)
                    .scaleType(mScaleType)
                    .placeholder(mBackgroundRef)
                    .failure(mFailureRef)
                    .create();
            mHolder = mLoaderWrapper.createView(getContext());
        } else {
            throw new IllegalArgumentException("ImageStub need a exactly size to layout");
        }
    }

    /**
     * Find view's size from {@code layout file Attributes}, or LayoutParams.
     * Still cannot find, resolve size after {@link #onLayout(boolean, int, int, int, int)}.
     */
    private void resolveResize() {
        int width = mLayoutWidth > 0 ? mLayoutWidth : getWidth();
        int height = mLayoutHeight > 0 ? mLayoutHeight : getHeight();
        //
        final LayoutParams lp = getLayoutParams();
        if (lp != null && lp.width > 0 && lp.height > 0) {
            width = lp.width;
            height = lp.height;
        }
        mLayoutWidth = width;
        mLayoutHeight = height;
    }


    /**
     * Inflates the layout resource identified by {@link ImageLoaderProvider#createImageView(Context)}}
     * and replaces this StubbedView in its parent by the inflated layout resource.
     */
    private void inflate() {
        //Inflate imageStub only once
        if (mLoaderWrapper != null) return;

        final ViewParent viewParent = getParent();
        if (!(viewParent instanceof ViewGroup)) {
            throw new IllegalStateException("ImageStub must have a non-null ViewGroup viewParent");
        }

        resolveResize();
        generateLoaderWrapper(mLayoutWidth, mLayoutHeight);

        if (mHolder != null) {
            ViewGroup parent = (ViewGroup) viewParent;

            if (parent instanceof AdapterView) {
                //setVisibility(VISIBLE);
                addView(mHolder, mLayoutWidth, mLayoutHeight);
            } else {
                final int index = parent.indexOfChild(this);
                parent.removeViewInLayout(this);

                if (getId() != NO_ID) {
                    mHolder.setId(getId());
                }
                LayoutParams layoutParams = getLayoutParams();

                if (layoutParams != null) {
                    parent.addView(mHolder, index, layoutParams);
                } else {
                    parent.addView(mHolder, index);
                }
            }
        }
    }


    public ImageView getImageView() {
        return mHolder;
    }

    public void setImageURI(@NonNull String uri) {
        if (!TextUtils.isEmpty(uri) && mLoaderWrapper != null) {
            mLoaderWrapper.load(getImageView(), Uri.parse(uri));
        }
        mImageUri = uri;
    }

    /**
     * Controls how the image should be resized or moved to match the size
     * of this ImageView.
     *
     * @param scaleType The desired scaling mode.
     */
    public void setScaleType(@PictureScaleType int scaleType) {
        mScaleType = scaleType;
    }

    public void setFailureImage(@DrawableRes int imageRef) {
        mFailureRef = imageRef;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        if (count > 0) {
            View child = getChildAt(0);
            final int width = Math.abs(r - l);
            final int height = Math.abs(b - t);
            child.layout(0, 0, width, height);
        }
        inflate();
        setImageURI(mImageUri);
    }

    private void notifyChanged() {

    }
}
