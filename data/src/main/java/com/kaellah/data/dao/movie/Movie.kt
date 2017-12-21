package com.kaellah.data.dao.movie

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = MovieDao.TABLE_MOVIES)
data class Movie(

        @PrimaryKey
        val id: Int,

        @ColumnInfo(name = "adult")
        val adult: Boolean,

        @ColumnInfo(name = "backdropPath")
        val backdropPath: String,

        @ColumnInfo(name = "originalLanguage")
        val originalLanguage: String,

        @ColumnInfo(name = "originalTitle")
        val originalTitle: String,

        @ColumnInfo(name = "overview")
        val overview: String,

        @ColumnInfo(name = "releaseDate")
        val releaseDate: String,

        @ColumnInfo(name = "posterPath")
        val posterPath: String,

        @ColumnInfo(name = "popularity")
        val popularity: Float,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "video")
        val video: Boolean,

        @ColumnInfo(name = "voteAverage")
        val voteAverage: Float,

        @ColumnInfo(name = "voteCount")
        val voteCount: Int
)