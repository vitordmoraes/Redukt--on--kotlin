package com.vitordmoraes.counterwithanvil.ui

import androidx.appcompat.app.AppCompatActivity
import com.github.raulccabreu.redukt.states.StateListener
import com.vitordmoraes.counterwithanvil.App
import com.vitordmoraes.counterwithanvil.model.AppState

abstract class AppLifecycleActivity : AppCompatActivity(), StateListener<AppState> {

    protected val state: AppState
        get() = App.redukt.state

    override fun onStart() {
        super.onStart()
        App.redukt.listeners.add(this)
        onChanged(state)
    }

    override fun onStop() {
        super.onStop()
        App.redukt.listeners.remove(this)
    }
}