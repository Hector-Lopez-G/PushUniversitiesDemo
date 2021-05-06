package com.hector.pushkitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;

public class MainActivity extends AppCompatActivity {
    final String TAG = "MyService";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getToken();
    }

    private void getToken() {
        // Create a thread.
        new Thread() {
            @Override
            public void run() {
                try {
                    // Obtain the app ID from the agconnect-service.json file.
                    String appId = AGConnectServicesConfig.fromContext(MainActivity.this).getString("client/app_id");

                    // Set tokenScope to HCM.
                    String tokenScope = "HCM";
                    String token = HmsInstanceId.getInstance(MainActivity.this).getToken(appId, tokenScope);
                    Log.i(TAG, "get token: " + token);

                    // Check whether the token is empty.
                    if(!TextUtils.isEmpty(token)) {
                        sendRegTokenToServer(token);
                    }
                } catch (ApiException e) {
                    Log.e(TAG, "get token failed, " + e);
                }
            }
        }.start();
    }
    private void sendRegTokenToServer(String token) {
        Log.i(TAG, "sending token to server. token:" + token);
    }
}