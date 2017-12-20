package com.kaellah.data.dao.movie

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = MovieDao.TABLE_MOVIES)
data class Movie(

        @PrimaryKey
        val id: String,

        @ColumnInfo(name = "avatar")
        val avatar: String,

        @ColumnInfo(name = "first_name")
        val firstName: String,

        @ColumnInfo(name = "last_name")
        val lastName: String

)