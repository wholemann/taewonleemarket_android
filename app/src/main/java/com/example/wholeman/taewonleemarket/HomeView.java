package com.example.wholeman.taewonleemarket;

import android.view.View;

interface HomeView {
    public interface Listener {
    }

    void registerListener(Listener listener);

    void unregisterListener(Listener listener);

    View getRootView();
}
