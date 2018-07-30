package com.kct.fundoHealth.btnotification;

import android.app.Application;

import com.mob.MobSDK;

/**
 * Created by ${Justin} on 2018/3/28.
 */

public class Tapp extends Application {

    private static Application instance;
    public static Application of(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        MobSDK.init(getApplicationContext(), "240c0b092385a", "e9a562158e2a32d96506092a8b2ef19f");
//        MobSDK.init(getApplicationContext(), "240c0b092385a", "e9a562158e2a32d96506092a8b2ef19f");
        MobSDK.init(getApplicationContext(), "12e59a65dfe80", "8995b606a9e6d3db563bbd40dab9ef53");
    }
}
