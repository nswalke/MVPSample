package com.nirmal.mvpdemo.search.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nirmal.mvpdemo.presenter.BasePresenter;
import com.nirmal.mvpdemo.search.adapter.SearchRecyclerAdapter;
import com.nirmal.mvpdemo.search.contract.SearchContract;
import com.nirmal.mvpdemo.search.model.SearchResult;

import java.util.List;

import javax.inject.Inject;

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter, SearchRecyclerAdapter.OnSearchResultClickListener {

    private SearchRecyclerAdapter searchRecyclerAdapter = null;
    private List<SearchResult> searchResults = null;

    @Inject
    protected SearchPresenter(SearchContract.View view) {
        super(view);
        searchRecyclerAdapter = new SearchRecyclerAdapter(searchResults,this);
    }

    @Override
    public void onViewCreated(@Nullable Bundle savedInstanceState, @Nullable Bundle arguments) {
        view.setSearchRecyclerAdapter(searchRecyclerAdapter);
    }

    @Override
    public void onSearchResultClick(@Nullable SearchResult searchResult) {
        //open details page of this search item
    }

    @Override
    public void onSearchTextChanged(String searchText) {
        //call search api
        //update searchResults list on API result
        //and notifyDataSetChanged
    }
}
