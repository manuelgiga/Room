package com.giga.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
        val note = Note(0, "el bosque de sauces", "trata de animales")

        GlobalScope.launch(Dispatchers.IO) {
            noteDao.insert(note)
            val allNotes = noteDao.getAll()
            withContext(Dispatchers.Main) {
                var extension = allNotes.size
                if (extension<5){
                    Toast.makeText(this@MainActivity, "medida: $extension", Toast.LENGTH_SHORT).show()
                    Log.d("Notes", allNotes.toString())
                }else{
                    Toast.makeText(this@MainActivity, "Ya no puedo mas", Toast.LENGTH_SHORT).show()
                    Log.d("Notes", allNotes.toString())
                }


            }
        }


    }
}

