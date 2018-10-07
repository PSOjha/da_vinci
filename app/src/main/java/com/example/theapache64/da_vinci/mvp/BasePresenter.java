package com.example.theapache64.da_vinci.mvp;

public class BasePresenter<V> {
    private final V view;

    public BasePresenter(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }
}
