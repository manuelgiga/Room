package com.giga.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
            val allNotes = noteDao.getAll()
                if(allNotes.isNullOrEmpty()) {
                    noteDao.insert(note)
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@MainActivity, "La lista está vacía", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    if (allNotes.size<2){
                        noteDao.insert(note)
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@MainActivity, "la medida es ${allNotes.size}", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@MainActivity, "Registro anulado", Toast.LENGTH_SHORT).show()
                            Toast.makeText(this@MainActivity, "Excede el numero maximo de registros por día", Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            withContext(Dispatchers.Main) {
                    Log.d("Notes", allNotes.toString())
            }
        }


    }
}

