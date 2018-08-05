package com.nirmal.mvpdemo.di.component;

import com.nirmal.mvpdemo.di.module.NetworkModule;
import com.nirmal.mvpdemo.di.scope.ApplicationScope;
import com.nirmal.mvpdemo.network.RestApiService;

import dagger.Component;

@ApplicationScope
@Component(modules = {NetworkModule.class})
public interface ApplicationComponent {

    RestApiService getRestApiService();
}
