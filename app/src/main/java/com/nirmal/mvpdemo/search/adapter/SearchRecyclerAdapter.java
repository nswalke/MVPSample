package com.nirmal.mvpdemo.search.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nirmal.mvpdemo.search.model.SearchResult;
import com.nirmal.mvpdemo.search.view.SearchResultViewHolder;

import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchResultViewHolder> implements SearchResultViewHolder.OnSearchResultItemClickListener {

    public interface OnSearchResultClickListener {
        void onSearchResultClick(@Nullable SearchResult searchResult);
    }

    private OnSearchResultClickListener onSearchResultClickListener = null;
    private List<SearchResult> searchResults = null;

    public SearchRecyclerAdapter(@Nullable List<SearchResult> searchResults, @Nullable OnSearchResultClickListener onSearchResultClickListener) {
        this.searchResults = searchResults;
        this.onSearchResultClickListener = onSearchResultClickListener;
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new SearchResultViewHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup, false, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder searchResultViewHolder, int position) {
        searchResultViewHolder.bindData(searchResults.get(position));
    }

    @Override
    public int getItemCount() {
        return searchResults != null ? searchResults.size() : 0;
    }

    @Override
    public void onSearchResultItemClick(int itemPosition) {
        if (itemPosition >= 0 && itemPosition < getItemCount()) {
            if (onSearchResultClickListener != null) {
                onSearchResultClickListener.onSearchResultClick(searchResults.get(itemPosition));
            }
        }
    }
}
