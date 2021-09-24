package com.vitordmoraes.counterwithanvil.actions

import com.github.raulccabreu.redukt.actions.Action
import com.vitordmoraes.counterwithanvil.App
import com.vitordmoraes.counterwithanvil.actions.Actions.ADD_VALUE
import com.vitordmoraes.counterwithanvil.actions.Actions.REMOVE_VALUE

class ActionCreator private constructor() {

    private object Holder {
        val INSTANCE = ActionCreator()
    }

    companion object {
        val instance: ActionCreator by lazy { Holder.INSTANCE }
    }

    private fun asyncDispatch(action: Action<*>) {
        App.redukt.dispatch(action, true)
    }

    fun addValue(sum: Int = 1) {
        asyncDispatch(Action(ADD_VALUE, sum))
    }

    fun removeValue(sub: Int = 1) {
        asyncDispatch(Action(REMOVE_VALUE, sub))
    }


}