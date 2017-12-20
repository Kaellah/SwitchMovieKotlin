package com.kaellah.data.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.kaellah.data.dao.movie.Movie
import com.kaellah.data.dao.movie.MovieDao


@Database(entities = arrayOf(Movie::class),
          version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        fun init(context: Context): AppDatabase {
            val builder = Room.databaseBuilder(context, AppDatabase::class.java, "app")
//            if (!BuildConfig.DEBUG) {
            builder.fallbackToDestructiveMigration()
//            }
            return builder.build()
        }
    }
}