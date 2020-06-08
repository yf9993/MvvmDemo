package com.yupaopao.mvvmdemo.core;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class RxViewModel extends ViewModel {

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    public void register(Disposable subscription) {
        mCompositeDisposable.add(subscription);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.clear();
    }
}
