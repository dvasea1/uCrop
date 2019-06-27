package com.yalantis.ucrop.view;

import android.content.Context;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.yalantis.ucrop.callback.CropBoundsChangeListener;
import com.yalantis.ucrop.callback.OverlayViewChangeListener;
import com.yalantis.ucrop.view.GestureCropImageView;
import com.yalantis.ucrop.view.UCropView;

/**
 * Created on 20/03/2017 16:57.
 */
public class ReusableUCropView extends UCropView {
    private GestureCropImageView reusableCropImageView;

    public ReusableUCropView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReusableUCropView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void resetCropImageView() {
        removeView(getCropImageView());
        reusableCropImageView = new GestureCropImageView(getContext());
        reusableCropImageView.setCropBoundsChangeListener(new CropBoundsChangeListener() {
            @Override
            public void onCropAspectRatioChanged(float cropRatio) {
                getOverlayView().setTargetAspectRatio(cropRatio);
            }
        });
        getOverlayView().setOverlayViewChangeListener(new OverlayViewChangeListener() {
            @Override
            public void onCropRectUpdated(RectF cropRect) {
                getCropImageView().setCropRect(cropRect);
            }
        });
        reusableCropImageView.setCropRect(getOverlayView().getCropViewRect());
        addView(reusableCropImageView, 0);
    }

    @NonNull
    @Override
    public GestureCropImageView getCropImageView() {
        return reusableCropImageView != null ? reusableCropImageView : super.getCropImageView();
    }
}