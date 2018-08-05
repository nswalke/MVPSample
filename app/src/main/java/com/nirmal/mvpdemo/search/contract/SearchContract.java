package com.nirmal.mvpdemo.search.contract;

import android.support.v7.widget.RecyclerView;

import com.nirmal.mvpdemo.view.BaseView;

public interface SearchContract {

    public interface View extends BaseView{

        void setSearchRecyclerAdapter(RecyclerView.Adapter adapter);
    }

    public interface Presenter{

        void onSearchTextChanged(String searchText);
    }
}
