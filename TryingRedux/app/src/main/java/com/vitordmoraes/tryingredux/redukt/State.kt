package com.vitordmoraes.tryingredux.redukt

import com.vitordmoraes.tryingredux.model.Todo
import org.reduxkotlin.createThreadSafeStore


data class AppState(
    val visibilityFilter: VisibilityFilter = VisibilityFilter.SHOW_ALL,
    val todos: List<Todo> = listOf())
{
    val visibleTodos: List<Todo>
        get() = getVisibleTodos(visibilityFilter)

    private fun getVisibleTodos(visibilityFilter: VisibilityFilter) = when (visibilityFilter) {
        VisibilityFilter.SHOW_ALL -> todos
        VisibilityFilter.SHOW_ACTIVE -> todos.filter { !it.completed }
        VisibilityFilter.SHOW_COMPLETED -> todos.filter { it.completed }
    }
}





