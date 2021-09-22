package com.vitordmoraes.tryingredux.redukt


sealed class Actions


data class AddTodo(val text: String, val completed: Boolean = false) : Actions()
data class ToggleTodo(val index: Int) : Actions()
data class SetVisibilityFilter(val visibilityFilter: VisibilityFilter) : Actions()
data class RemoveDoneTodos(val state: AppState) : Actions()


enum class VisibilityFilter{
    SHOW_ALL,
    SHOW_COMPLETED,
    SHOW_ACTIVE
}