package com.nirmal.mvpdemo.search.di;

import com.nirmal.mvpdemo.di.scope.ViewScope;
import com.nirmal.mvpdemo.search.contract.SearchContract;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {

    private final SearchContract.View view;

    public SearchModule(SearchContract.View view) {
        this.view = view;
    }

    @Provides
    @ViewScope
    public SearchContract.View getView() {
        return view;
    }
}
