package com.fitraditya.reactnativertmp;

import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.pedro.builder.RtmpBuilder;
import net.ossrs.rtmp.ConnectCheckerRtmp;

/**
 * Created by fitra on 06/07/17.
 */

public class RTMPStreamingViewManager extends SimpleViewManager<View> implements SurfaceHolder.Callback, ConnectCheckerRtmp {
    private SurfaceHolder surfaceHolder;
    private RtmpBuilder rtmpBuilder;
    private RTMPSurfaceView surfaceView;

    @Override
    public String getName() {
        return "RTMPStreamingView";
    }

    @Override
    protected View createViewInstance(ThemedReactContext reactContext) {
        Log.d("TAG", "Di sini 1");
        surfaceView = new RTMPSurfaceView(reactContext);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        return surfaceView;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("TAG", "Di sini 2");
        rtmpBuilder = new RtmpBuilder(surfaceView, this);

        if (rtmpBuilder.prepareAudio() && rtmpBuilder.prepareVideo()) {
            rtmpBuilder.startStream("rtmp://rtc.qiscus.com/live360p/testing1");
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (rtmpBuilder != null && rtmpBuilder.isStreaming()) {
            rtmpBuilder.stopStream();
        }
    }

    @Override
    public void onConnectionSuccessRtmp() {

    }

    @Override
    public void onConnectionFailedRtmp() {

    }

    @Override
    public void onDisconnectRtmp() {

    }

    @Override
    public void onAuthErrorRtmp() {

    }

    @Override
    public void onAuthSuccessRtmp() {

    }
}
