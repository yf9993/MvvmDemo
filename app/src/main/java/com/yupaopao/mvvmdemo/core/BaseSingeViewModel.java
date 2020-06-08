package com.yupaopao.mvvmdemo.core;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public abstract class  BaseSingeViewModel<T> extends RxViewModel {
    private MutableLiveData<T> data=new MutableLiveData<T>();

    protected abstract void request();

    protected void onSuccess(T t){
        data.setValue(t);
    }

    protected void onFailed(T t){
        data.setValue(t);
    }

    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer){
        data.observe(owner,observer);
    }

}
