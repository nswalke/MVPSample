package com.nirmal.mvpdemo.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nirmal.mvpdemo.view.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends BaseView> {

    protected final V view;

    private CompositeDisposable compositeDisposable = null;

    protected BasePresenter(V view){
        this.view = view;
        this.compositeDisposable = new CompositeDisposable();
    }

    /**
     * Contains common setup actions needed for all presenters, if any.
     * Subclasses may override this.
     */
    public void start() {
    }

    /**
     * Contains common cleanup actions needed for all presenters, if any.
     * Subclasses may override this.
     */
    public void stop() {
        compositeDisposable.clear();
    }

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public abstract void onViewCreated(@Nullable Bundle savedInstanceState, @Nullable Bundle arguments);
}
