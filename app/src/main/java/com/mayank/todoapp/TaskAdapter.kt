package com.mayank.todoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_item.view.*

class TaskAdapter(val list:ArrayList<Task>,val context: Context): RecyclerView.Adapter<TaskViewHolder>()
{
    fun updateTasks(newTasks: ArrayList<Task>) {
        list.clear()
        list.addAll(newTasks)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflator = LayoutInflater.from(context)
        val view = inflator.inflate(R.layout.task_item,parent,false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task_item = list[position]
        holder.itemView.apply{
            task_output.text = task_item.task
            Done.isChecked  = task_item.done
        }
    }
}

class TaskViewHolder(itemview : View) :RecyclerView.ViewHolder(itemview)
