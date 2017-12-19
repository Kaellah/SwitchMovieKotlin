package com.kaellah.switchappkotlin

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import javax.inject.Inject


class AppLifecycleObserver @Inject constructor(private val context: Context) : LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onExitForeground() {
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onEnterForeground() {
    }

}