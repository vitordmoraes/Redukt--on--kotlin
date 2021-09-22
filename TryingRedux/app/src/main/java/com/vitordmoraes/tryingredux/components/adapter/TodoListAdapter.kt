package com.vitordmoraes.tryingredux.components.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vitordmoraes.tryingredux.R
import com.vitordmoraes.tryingredux.model.Todo
import com.vitordmoraes.tryingredux.redukt.ToggleTodo
import com.vitordmoraes.tryingredux.store
import kotlinx.android.synthetic.main.todo_list_item.view.*

class TodoListAdapter : ListAdapter<Todo,ViewHolder >(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }
}

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    fun bind(todo: Todo){
        if (todo.completed) {
            itemView.textTodo.paintFlags =itemView.textTodo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            itemView.textTodo.paintFlags =
                itemView.textTodo.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        itemView.textTodo.text = todo.text
        itemView.cbTodo.isChecked = todo.completed
        itemView.cbTodo.setOnCheckedChangeListener { buttonView, isChecked ->
            store.dispatch(ToggleTodo(adapterPosition))
        }
    }

}

class DiffCallback : DiffUtil.ItemCallback<Todo>(){
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }

}