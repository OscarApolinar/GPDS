package com.example.paleteriadjn

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Producto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productos(): ProductosDao

    companion object {
        @Volatile
        private  var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            var tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" //Nombre de la base de datos
                ).build()
                INSTANCE = instance

                return  instance
            }
        }
    }

}