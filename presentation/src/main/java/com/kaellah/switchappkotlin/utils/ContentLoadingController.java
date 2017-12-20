package com.kaellah.switchappkotlin.utils;

import android.os.Handler;
import android.os.Looper;

public class ContentLoadingController {

    private static final int MIN_SHOW_TIME = 500; // ms
    private static final int MIN_DELAY = 500; // ms

    private final Handler handler = new Handler(Looper.getMainLooper());
    boolean postedHide = false;
    boolean postedShow = false;
    boolean dismissed = false;
    private ContentLoadingListener listener;
    private long startTime = -1;

    private final Runnable delayedHide = new Runnable() {

        @Override
        public void run() {
            postedHide = false;
            startTime = -1;
            if (listener != null) listener.onHideContentProgress();
        }
    };

    private final Runnable delayedShow = new Runnable() {

        @Override
        public void run() {
            postedShow = false;
            if (!dismissed) {
                startTime = System.currentTimeMillis();
                if (listener != null) listener.onShowContentProgress();
            }
        }
    };

    public void setListener(ContentLoadingListener listener) {
        this.listener = listener;
    }

    public void clear() {
        handler.removeCallbacks(delayedHide);
        handler.removeCallbacks(delayedShow);
    }

    public void show() {
        // Reset the start time.
        startTime = -1;
        dismissed = false;
        handler.removeCallbacks(delayedHide);
        if (!postedShow) {
            handler.postDelayed(delayedShow, MIN_DELAY);
            postedShow = true;
        }
    }

    public void hide() {
        dismissed = true;
        handler.removeCallbacks(delayedShow);
        long diff = System.currentTimeMillis() - startTime;
        if (diff >= MIN_SHOW_TIME || startTime == -1) {
            // The progress spinner has been shown long enough
            // OR was not shown yet. If it wasn't shown yet,
            // it will just never be shown.
            if (listener != null) listener.onHideContentProgress();
        } else {
            // The progress spinner is shown, but not long enough,
            // so put a delayed message in to hide it when its been
            // shown long enough.
            if (!postedHide) {
                handler.postDelayed(delayedHide, MIN_SHOW_TIME - diff);
                postedHide = true;
            }
        }
    }

    public interface ContentLoadingListener {

        void onShowContentProgress();

        void onHideContentProgress();
    }
}
