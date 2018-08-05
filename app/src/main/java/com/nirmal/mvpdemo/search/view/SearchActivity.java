package com.nirmal.mvpdemo.search.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.nirmal.mvpdemo.MyApplication;
import com.nirmal.mvpdemo.R;
import com.nirmal.mvpdemo.search.contract.SearchContract;
import com.nirmal.mvpdemo.search.di.DaggerSearchComponent;
import com.nirmal.mvpdemo.search.di.SearchModule;
import com.nirmal.mvpdemo.search.presenter.SearchPresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {

    @Inject
    protected SearchPresenter presenter;

    private EditText etSearch;
    private RecyclerView rvSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        DaggerSearchComponent.builder()
                .applicationComponent(MyApplication.getInstance().getApplicationComponent())
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);

        initView();
        presenter.onViewCreated(savedInstanceState, null);
    }

    private void initView() {
        etSearch = findViewById(R.id.et_search);
        rvSearchResults = findViewById(R.id.rv_search_results);
        rvSearchResults.setLayoutManager(new LinearLayoutManager(this));
        presenter.addDisposable(
                RxTextView.textChanges(etSearch)
                        .filter(charSequence -> charSequence.length() > 2)
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .map(charSequence -> charSequence.toString())
                        .subscribe(searchText -> {
                            presenter.onSearchTextChanged(searchText);
                        }));
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public boolean isAdded() {
        return !isFinishing();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setSearchRecyclerAdapter(RecyclerView.Adapter adapter) {
        rvSearchResults.setAdapter(adapter);
    }
}
