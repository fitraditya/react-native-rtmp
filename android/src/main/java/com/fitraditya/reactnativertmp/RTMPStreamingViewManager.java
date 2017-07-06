package com.fitraditya.reactnativertmp;

import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

/**
 * Created by fitra on 06/07/17.
 */

public class RTMPStreamingViewManager extends SimpleViewManager<View> {
    private static RTMPSurfaceView surfaceView;

    @Override
    public String getName() {
        return "RTMPStreamingView";
    }

    @Override
    protected View createViewInstance(ThemedReactContext reactContext) {
        surfaceView = new RTMPSurfaceView(reactContext);
        return surfaceView;
    }

    public static RTMPSurfaceView getSurfaceView() {
        return surfaceView;
    }
}
