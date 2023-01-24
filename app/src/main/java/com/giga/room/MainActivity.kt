package com.giga.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = AppDatabase(applicationContext)
        val taskDao = db.taskDao()

        GlobalScope.launch(Dispatchers.IO) {
            taskDao.insertTask(Task(0, "Title", "Description", false))
            val tasks = taskDao.getAllTasks()
            delay(2_000)
            Log.d("dateichon", "la data es: ${tasks.value}")
        }






    }
}