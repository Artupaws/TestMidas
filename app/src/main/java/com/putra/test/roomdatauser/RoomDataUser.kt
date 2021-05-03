package com.putra.test.roomdatauser

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataUser::class], version = 1, exportSchema = false)
abstract class RoomDataUser :RoomDatabase(){

    companion object {

        @Volatile
        private var INSTANCE: RoomDataUser? = null

        fun getDatabase(context: Context):RoomDataUser{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, RoomDataUser::class.java,"passenger_db"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

    abstract fun getDataUser():UserDao

}