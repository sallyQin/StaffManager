package cn.studyjams.s1.sj52.staffmanagementsystem;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 1 on 2016/10/16.
 */

public class FrescoApplication extends Application {

    public void onCreate(){
        super.onCreate();
        Fresco.initialize(this);
    }
}
