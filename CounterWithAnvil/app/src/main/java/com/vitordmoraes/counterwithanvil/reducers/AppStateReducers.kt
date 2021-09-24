package com.vitordmoraes.counterwithanvil.reducers
import com.github.raulccabreu.redukt.actions.Reduce
import com.github.raulccabreu.redukt.reducers.BaseAnnotatedReducer
import com.vitordmoraes.counterwithanvil.actions.Actions
import com.vitordmoraes.counterwithanvil.model.AppState


class AppStateReducers : BaseAnnotatedReducer<AppState>() {

    @Reduce(Actions.ADD_VALUE)
    fun addValue(state: AppState, newValue:Int): AppState {
        state.count.let { return state.copy(count = state.count + newValue) }
    }

    @Reduce(Actions.REMOVE_VALUE)
    fun removeValue(state: AppState, newValue: Int): AppState {
        state.count.let { return state.copy(count = state.count - newValue) }
    }



}