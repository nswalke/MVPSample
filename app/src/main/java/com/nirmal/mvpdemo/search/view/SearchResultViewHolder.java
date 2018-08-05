package com.nirmal.mvpdemo.search.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nirmal.mvpdemo.R;
import com.nirmal.mvpdemo.search.model.SearchResult;

public class SearchResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public interface OnSearchResultItemClickListener {
        void onSearchResultItemClick(int itemPosition);
    }

    private TextView tvTitle;

    private OnSearchResultItemClickListener onSearchResultItemClickListener = null;

    public SearchResultViewHolder(@NonNull LayoutInflater layoutInflater, ViewGroup parent, boolean attachToRoot, @Nullable OnSearchResultItemClickListener onSearchResultItemClickListener) {
        super(layoutInflater.inflate(R.layout.layout_search_result, parent, attachToRoot));
        this.onSearchResultItemClickListener = onSearchResultItemClickListener;
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvTitle.setOnClickListener(this);
    }

    public void bindData(@Nullable SearchResult searchResult) {
        tvTitle.setText(searchResult == null ? "" : searchResult.title);
    }

    @Override
    public void onClick(View view) {
        int itemPosition = getAdapterPosition();
        switch (view.getId()) {
            case R.id.tv_title:
                if (onSearchResultItemClickListener != null) {
                    onSearchResultItemClickListener.onSearchResultItemClick(itemPosition);
                }
                break;
        }
    }
}
