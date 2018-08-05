package com.nirmal.mvpdemo;

import android.app.Application;

import com.nirmal.mvpdemo.di.component.ApplicationComponent;
import com.nirmal.mvpdemo.di.component.DaggerApplicationComponent;
import com.nirmal.mvpdemo.di.module.ContextModule;

public class MyApplication extends Application {

    private static MyApplication instance = null;

    private ApplicationComponent applicationComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static MyApplication getInstance(){
        return instance;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
