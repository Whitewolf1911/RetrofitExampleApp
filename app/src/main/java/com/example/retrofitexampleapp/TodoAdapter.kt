package com.example.retrofitexampleapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.retrofitexampleapp.databinding.ActivityMainBinding
import com.example.retrofitexampleapp.databinding.ItemTodoBinding

class TodoAdapter() : RecyclerView.Adapter<TodoAdapter.todoItemViewHolder>() {

    inner class todoItemViewHolder(val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallBack)
    var todos: List<Todo>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(layoutInflater, parent, false)
        return todoItemViewHolder(binding)

    }

    override fun onBindViewHolder(holder: todoItemViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            tvTitle.text = todo.title
            cbDone.isChecked = todo.completed
        }

    }

    override fun getItemCount() = todos.size


}