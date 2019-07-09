package com.mayank.todoapp

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class TaskTable {

    companion object{

        val TABLE_NAME = "todo"

        val CMD_CREATE_TABLE = """
            CREATE TABLE $TABLE_NAME (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            task TEXT,
            done BOOLEAN
            );
        """.trimIndent()

        fun insertTask(db: SQLiteDatabase,task:Task){
            val row = ContentValues()
            row.put("task",task.task)
            row.put("done",task.done)

            db.insert(TABLE_NAME,null,row)
        }

        fun getAllTask(db:SQLiteDatabase): ArrayList<Task> {

            val tasks = arrayListOf<Task>()

            val cursor = db.query(
                TABLE_NAME,
                arrayOf("id","task","done"),
                null,
                null,
                null,
                null,
                null
            )

            cursor.moveToFirst()

            val idcol = cursor.getColumnIndexOrThrow("id")
            val taskcol= cursor.getColumnIndexOrThrow("task")
            val donecol = cursor.getColumnIndexOrThrow("done")

            while (cursor.moveToNext()) {
                val task = Task(
                    cursor.getInt(idcol),
                    cursor.getString(taskcol),
                    cursor.getInt(donecol) == 1
                )
                tasks.add(task)
            }

            cursor.close()
            return tasks
        }
    }

}
