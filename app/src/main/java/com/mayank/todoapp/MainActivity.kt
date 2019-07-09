package com.mayank.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.task_item.*

class MainActivity : AppCompatActivity() {

    var task_list = arrayListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TaskAdapter(task_list,this)
        rvtask.adapter = adapter

        Add.setOnClickListener{

        }

        Delete_all.setOnClickListener{


        }

        Delete_task.setOnClickListener{

        }

        Done.setOnClickListener {

        }

    }
}
