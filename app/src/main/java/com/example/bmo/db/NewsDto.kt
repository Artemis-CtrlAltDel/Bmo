package com.example.bmo.db

import androidx.room.TypeConverter
import com.example.bmo.pojo.Source
import com.google.gson.Gson

class NewsDto {

    @TypeConverter
    fun Source.to_gson(): String =
        Gson().toJson(this)

    @TypeConverter
    fun String.to_news(): Source =
        Gson().fromJson(this, Source::class.java)
}