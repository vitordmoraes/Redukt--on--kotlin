package com.vitordmoraes.tryingredux.redukt

import com.vitordmoraes.tryingredux.model.Todo


fun todosReducer(state: List<Todo>, action: Any) =
    when(action) {
        is AddTodo -> state.plus(
            Todo(
                text = action.text,
                completed = false
            )
        )
        is ToggleTodo -> state.mapIndexed { index, todo ->
            if (index == action.index) {
                todo.copy(completed = !todo.completed)
            } else {
                todo
            }
        }
        is RemoveDoneTodos -> state.filter { !it.completed }.map { it.copy()}
        else -> state
    }

fun visibilityFilterReducer(state: VisibilityFilter, action: Any): VisibilityFilter =
    when (action) {
        is SetVisibilityFilter -> action.visibilityFilter
        else -> state
    }

fun rootReducer (state: AppState, action: Any) = AppState(
    todos = todosReducer(state.todos, action),
    visibilityFilter = visibilityFilterReducer(state.visibilityFilter, action)
)