package com.hector.pushkitdemo;

import android.util.Log;

import com.huawei.hms.push.HmsMessageService;

public class MyPushService extends HmsMessageService {
    private static final String TAG = "PushDemoLog";
    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.i(TAG, "receive token:" + token);
    }
}
