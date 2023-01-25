package com.giga.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        val noteDao = db.noteDao()
        val note = Note(0, "title", "content")

        GlobalScope.launch(Dispatchers.IO) {
            noteDao.insert(note)
            val allNotes = noteDao.getAll()
            withContext(Dispatchers.Main) {
                Log.d("Notes", allNotes.toString())
            }
        }


    }
}

