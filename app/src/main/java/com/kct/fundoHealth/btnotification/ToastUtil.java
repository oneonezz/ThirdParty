package com.kct.fundoHealth.btnotification;

import android.widget.Toast;

/**
 * Created by ${Justin} on 2017/12/1.
 */

public class ToastUtil {

    private static boolean isTest = true;

    /**
     *  所有测试显示都用这个方法
     */
    public static void tShow(String content) {
        if(isTest)
            Toast.makeText(Tapp.of(),content, Toast.LENGTH_SHORT).show();
    }

    /**所有生产代码可能会用到的Toast都调这个方法*/
    public static void mayShow(String content) {
        Toast.makeText(Tapp.of(),content, Toast.LENGTH_SHORT).show();
    }

    /**异常Toast,用来给自己看的,发版本时注释掉*/
    public static void eShow(String content) {
        Toast.makeText(Tapp.of(),content, Toast.LENGTH_SHORT).show();
    }
}
