package com.qbeenslee.imagestub.adapter.fresco;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.qbeenslee.imagestub.annotation.PictureScaleType;
import com.qbeenslee.imagestub.interfaces.ImageLoaderProvider;

/**
 * Fresco
 */
final class FrescoProvider implements ImageLoaderProvider {


    @Override
    public ImageView createImageView(@NonNull Context context) {
        return new SimpleDraweeView(context);
    }

    @Override
    public void paintImageView(@NonNull ImageView view, @NonNull Uri uri, int width, int height, @PictureScaleType int scaleType, @DrawableRes int placeholderImage, @DrawableRes int failureImage) {
        if (view instanceof SimpleDraweeView && width > 0 && height > 0) {
            final SimpleDraweeView draweeView = (SimpleDraweeView) view;
            ImageRequestBuilder requestBuilder = ImageRequestBuilder.newBuilderWithSource(uri);
            requestBuilder.setResizeOptions(new ResizeOptions(width, height));
            requestBuilder.setProgressiveRenderingEnabled(true);
            GenericDraweeHierarchyBuilder hierarchyBuilder = new GenericDraweeHierarchyBuilder(draweeView.getContext().getResources());

            if (placeholderImage != 0) {
                hierarchyBuilder.setPlaceholderImage(placeholderImage);
            }
            if (failureImage != 0) {
                hierarchyBuilder.setFailureImage(failureImage);
            }

            //hierarchyBuilder.setActualImageScaleType()

            PipelineDraweeControllerBuilder controllerBuilder = Fresco.newDraweeControllerBuilder();
            controllerBuilder.setImageRequest(requestBuilder.build());

            draweeView.setHierarchy(hierarchyBuilder.build());
            draweeView.setController(controllerBuilder.build());
        }
    }
}
