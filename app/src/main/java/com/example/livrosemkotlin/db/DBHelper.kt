package com.example.livrosemkotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.livrosemkotlin.beans.*

@Database(
    version = 1,
    exportSchema = false,
    entities =[
        Livro::class,
        Editora::class
    ],
)

abstract class DBHelper : RoomDatabase() {

    abstract fun mainDAO(): MainDAO

    companion object {
        @Volatile
        private var INSTANCE: DBHelper? = null

        fun getInstance(context: Context): DBHelper {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DBHelper::class.java,
                    "dblivros"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}