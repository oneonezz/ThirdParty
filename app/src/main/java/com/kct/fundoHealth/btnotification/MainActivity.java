package com.kct.fundoHealth.btnotification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dd.plist.NSDictionary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    /** Button */
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                Toast.makeText(this, "" + getPackageName(), Toast.LENGTH_LONG).show();
                break;
        }
    }


    public static String getStringFromInputStream(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private void wxLogin() {

        Toast.makeText(MainActivity.this, "Wechat登录开始", Toast.LENGTH_SHORT).show();
        final Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                /*final Platform qq = ShareSDK.getPlatform(QQ.NAME

);*/
                /*final Platform sinaweibo = ShareSDK.getPlatform(SinaWeibo.NAME

);*/
        if (wechat.isClientValid()) {
            //客户端可用
        }

        if (wechat.isAuthValid()) {
            wechat.removeAccount(true);
        }

        wechat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, final HashMap<String, Object> hashMap) {
                /*platform.getDb().exportData()获取用户数据*/
                Log.d("ShareSDK", "onComplete ---->  登录成功" + platform.getDb().exportData());
                platform.getDb().getUserId();
                // 这里授权成功跳转到程序主界面了
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.tShow("成功");
                    }
                });
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.d("ShareSDK", "onError ---->  登录失败" + throwable.toString());
                Log.d("ShareSDK", "onError ---->  登录失败" + throwable.getStackTrace().toString());
                Log.d("ShareSDK", "onError ---->  登录失败" + throwable.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.tShow("onError");
                    }
                });
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.d("ShareSDK", "onCancel ---->  登录取消");
                ToastUtil.tShow("onCancel");
            }
        });
        wechat.SSOSetting(false);
        wechat.showUser(null);

//        Log.e(TAG, "wxLogin: ");
//        Platform wechat= ShareSDK.getPlatform(Wechat.NAME);
//        wechat.SSOSetting(true);
//        wechat.setPlatformActionListener(new WcListener());
//        wechat.authorize();
    }

    class WcListener implements PlatformActionListener {

        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            ToastUtil.tShow("成功");
            Log.e(TAG, "onComplete: ");
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            ToastUtil.tShow("失败");
            Log.e(TAG, "onError: ");
        }

        @Override
        public void onCancel(Platform platform, int i) {
            ToastUtil.tShow("onCancel");
            Log.i(TAG, "onCancel: ");
        }
    }
}
