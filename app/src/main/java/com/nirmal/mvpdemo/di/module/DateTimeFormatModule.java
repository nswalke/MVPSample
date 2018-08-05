package com.nirmal.mvpdemo.di.module;

import com.nirmal.mvpdemo.di.qualifier.RestApiDateTimeFormat;
import com.nirmal.mvpdemo.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DateTimeFormatModule {

    @Provides
    @ApplicationScope
    @RestApiDateTimeFormat
    public String getRestApiDateTimeFormat(){
        return "yyyy-MM-dd'T'HH:mm:ss.SSSz";
    }
}
