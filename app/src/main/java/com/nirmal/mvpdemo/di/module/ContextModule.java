package com.nirmal.mvpdemo.di.module;

import android.content.Context;

import com.nirmal.mvpdemo.di.qualifier.ApplicationContext;
import com.nirmal.mvpdemo.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private final Context applicationContext;

    public ContextModule(Context context) {
        this.applicationContext = context.getApplicationContext();
    }

    @Provides
    @ApplicationContext
    @ApplicationScope
    public Context getApplicationContext() {
        return applicationContext;
    }
}
