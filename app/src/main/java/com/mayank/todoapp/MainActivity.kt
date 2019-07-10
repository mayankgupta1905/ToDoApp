package com.mayank.todoapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var task_list = arrayListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbhelper = SQLiteHelper(this)
        val todotask = dbhelper.writableDatabase


        task_list = TaskTable.getAllTask(todotask)


        val adapter = TaskAdapter(task_list,this)
        rvtask.layoutManager = LinearLayoutManager(this)
        rvtask.adapter = adapter


        adapter.todoItemClickListener = object : TodoItemClickListener {
            override fun onDoneClick(task: Task, position: Int) {
                if(task.done)
                    task.done = false
                else
                    task.done = true
                TaskTable.updateTask(
                    todotask,task
                )
                task_list[position].done = task.done
                adapter.updateTasks(TaskTable.getAllTask(todotask))
            }

            override fun onDeleteClick(task: Task, position: Int) {
               TaskTable.deleteTask(todotask, task.id!!)
                task_list.removeAt(position)
                adapter.updateTasks(TaskTable.getAllTask(todotask))
            }
        }

        Add.setOnClickListener{
            TaskTable.insertTask(
                todotask,Task(id = null,task = task_input.text.toString(),done = false)
            )
            adapter.updateTasks(TaskTable.getAllTask(todotask))
            adapter.notifyDataSetChanged()
            Log.i("Insertion","Data Inserted")
        }

        Delete_all.setOnClickListener{
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.apply {
                setTitle("Delete All")
                setMessage("Do you Want to Delete The To do List ?")
                setPositiveButton("Yes"){dialog, which ->  
                    task_list.clear()
                    adapter.notifyDataSetChanged()

                    TaskTable.deleteAll(todotask)

                    Toast.makeText(this@MainActivity,"List Deleted",Toast.LENGTH_SHORT).show()
                    Log.i("Delete All","all deleted")
                }
                setNegativeButton("No"){dialog, which ->
                    Toast.makeText(this@MainActivity,"List Not Deleted",Toast.LENGTH_SHORT).show()
                }
            }
            val dialog = builder.create()

            dialog.show()
        }

    }
}
