package com.example.paleteriadjn

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Proveedor::class], version = 1)
abstract class AppDatabaseProv : RoomDatabase() {

    abstract fun proveedores(): ProveedoresDao

    companion object {
        @Volatile
        private  var INSTANCE: AppDatabaseProv? = null
        fun getDatabase(context: Context): AppDatabaseProv {
            var tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabaseProv::class.java,
                    "app_database_proveedores" //Nombre de la base de datos
                ).build()
                INSTANCE = instance

                return  instance
            }
        }
    }

}