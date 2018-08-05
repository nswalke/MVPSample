package com.nirmal.mvpdemo.search.di;

import com.nirmal.mvpdemo.di.component.ApplicationComponent;
import com.nirmal.mvpdemo.di.scope.ViewScope;
import com.nirmal.mvpdemo.search.view.SearchActivity;

import dagger.Component;

@ViewScope
@Component(dependencies = {ApplicationComponent.class}, modules = {SearchModule.class})
public interface SearchComponent {

    void inject(SearchActivity target);
}
