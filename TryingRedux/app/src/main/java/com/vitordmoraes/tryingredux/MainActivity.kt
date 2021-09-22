package com.vitordmoraes.tryingredux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.vitordmoraes.tryingredux.components.adapter.TodoListAdapter
import com.vitordmoraes.tryingredux.redukt.*
import kotlinx.android.synthetic.main.activity_main.*
import org.reduxkotlin.StoreSubscription
import org.reduxkotlin.createThreadSafeStore



val store = createThreadSafeStore(::rootReducer, AppState())

class MainActivity : AppCompatActivity() {

    private lateinit var storeSubscription: StoreSubscription
    private val adapter by lazy { TodoListAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        storeSubscription = store.subscribe { render(store.state) }
        initUi()

        render(store.state)
    }



    private fun render (state: AppState) {
        adapter.submitList(state.visibleTodos)
    }


    private fun initUi() {
        recyclerView.adapter = adapter

        btnDeleteDone.setOnClickListener {
            store.dispatch(RemoveDoneTodos(store.state))
        }


        btnAddTodo.setOnClickListener {
            val edtText = etTodo.text.toString()
            if (edtText.isEmpty()) {
                Toast.makeText(this, "Cannot store empty TODOS", Toast.LENGTH_SHORT).show()
            } else {
                store.dispatch(AddTodo(edtText))
                etTodo.text.clear()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.filter_visibility_menu, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.showAll -> {
                store.dispatch(SetVisibilityFilter(VisibilityFilter.SHOW_ALL))
                true
            }
            R.id.showCompleted -> {
                store.dispatch(SetVisibilityFilter(VisibilityFilter.SHOW_COMPLETED))
                true
            }
            R.id.showActive -> {
                store.dispatch(SetVisibilityFilter(VisibilityFilter.SHOW_ACTIVE))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
