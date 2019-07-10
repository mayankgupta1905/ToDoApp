package com.mayank.todoapp

interface TodoItemClickListener {

    fun onDeleteClick(task:Task,position:Int)

    fun onDoneClick(task:Task,position:Int)
}