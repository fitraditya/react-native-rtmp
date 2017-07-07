package com.fitraditya.reactnativertmp;

import android.widget.Toast;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.pedro.builder.RtmpBuilder;

import net.ossrs.rtmp.ConnectCheckerRtmp;

import java.util.HashMap;
import java.util.Map;

public class RTMPModule extends ReactContextBaseJavaModule {
    private static RTMPSurfaceView surfaceView;
    private static RtmpBuilder rtmpBuilder;
    private static boolean isSurfaceCreated;

    public RTMPModule(ReactApplicationContext reactContext) {
        super(reactContext);
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
        }

        promise.resolve(!rtmpBuilder.isStreaming());
    }

    @ReactMethod
    public void switchCamera(Promise promise) {
        if (rtmpBuilder != null && rtmpBuilder.isStreaming()) {
            rtmpBuilder.switchCamera();
        }

        promise.resolve(rtmpBuilder.isStreaming());
    }

    public static void setSurfaceView(RTMPSurfaceView surface) {
        surfaceView = surface;
        rtmpBuilder = new RtmpBuilder(surfaceView, new ConnectCheckerRtmp() {
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
        });

        isSurfaceCreated = true;
    }

    public static void destroySurfaceView() {
        if (rtmpBuilder != null && rtmpBuilder.isStreaming()) {
            rtmpBuilder.stopStream();
            rtmpBuilder = null;
        }

        isSurfaceCreated = false;
    }
}
