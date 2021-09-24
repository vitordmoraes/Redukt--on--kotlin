package com.vitordmoraes.counterwithanvil

import android.app.Application
import android.content.Context
import com.github.raulccabreu.redukt.Redukt
import com.vitordmoraes.counterwithanvil.model.AppState
import com.vitordmoraes.counterwithanvil.reducers.AppStateReducers

class App : Application() {

    companion object {
        lateinit var redukt: Redukt<AppState>

        fun initRedukt(context:Context, appState: AppState): Redukt<AppState> {
            val redukt = Redukt(appState)

            addReducers(redukt)

            return redukt
        }

        private fun addReducers(redukt: Redukt<AppState>) {
            redukt.reducers["appStateReducers"] = AppStateReducers()
        }
    }

    override fun onCreate() {
        super.onCreate()

        initRedukt(applicationContext, AppState()).let {
            redukt = it
        }
    }

    override fun onTerminate() {
        redukt.stop()
        super.onTerminate()
//        ObjectBox.init(this)
    }


}