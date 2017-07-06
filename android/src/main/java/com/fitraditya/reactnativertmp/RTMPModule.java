package com.fitraditya.reactnativertmp;

import android.view.SurfaceHolder;
import android.widget.Toast;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.pedro.builder.RtmpBuilder;

import net.ossrs.rtmp.ConnectCheckerRtmp;

import java.util.HashMap;
import java.util.Map;

public class RTMPModule extends ReactContextBaseJavaModule implements SurfaceHolder.Callback, ConnectCheckerRtmp {
    private RTMPSurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private RtmpBuilder rtmpBuilder;
    private boolean isSurfaceCreated;

    public RTMPModule(ReactApplicationContext reactContext) {
        super(reactContext);
        surfaceView = RTMPStreamingViewManager.getSurfaceView();
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        rtmpBuilder = new RtmpBuilder(surfaceView, this);
    }

    @Override
    public String getName() {
        return "RTMPModule";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }

    @ReactMethod
    public void show(String message) {
        Toast.makeText(getReactApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @ReactMethod
    public void startStream(String rtmpUrl, Promise promise) {
        if (isSurfaceCreated) {
            if (rtmpBuilder != null && !rtmpBuilder.isStreaming() && rtmpBuilder.prepareAudio() && rtmpBuilder.prepareVideo()) {
                rtmpBuilder.startStream(rtmpUrl);
            } else {
                Toast.makeText(getReactApplicationContext(), "Failed to preparing RTMP builder.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getReactApplicationContext(), "Surface view is not ready.", Toast.LENGTH_SHORT).show();
        }

        promise.resolve(rtmpBuilder.isStreaming());
    }

    @ReactMethod
    public void stopStream(Promise promise) {
        if (rtmpBuilder != null && rtmpBuilder.isStreaming()) {
            rtmpBuilder.stopStream();
            rtmpBuilder = null;
        }

        promise.resolve(!rtmpBuilder.isStreaming());
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isSurfaceCreated = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (rtmpBuilder != null && rtmpBuilder.isStreaming()) {
            rtmpBuilder.stopStream();
            rtmpBuilder = null;
        }

        isSurfaceCreated = false;
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
