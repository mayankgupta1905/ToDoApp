package com.mayank.todoapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.task_item.*

class MainActivity : AppCompatActivity() {

    var task_list = arrayListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbhelper = SQLiteHelper(this)
        val todotask = dbhelper.writableDatabase


        task_list = TaskTable.getAllTask(todotask)


        val adapter = TaskAdapter(task_list,this)
        rvtask.adapter = adapter

        Add.setOnClickListener{
            TaskTable.insertTask(
                todotask,Task(id = null,task = task_input.text.toString(),done = false)
            )
            adapter.updateTasks(TaskTable.getAllTask(todotask))
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
