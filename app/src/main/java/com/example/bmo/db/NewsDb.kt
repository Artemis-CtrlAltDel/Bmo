package com.example.bmo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bmo.pojo.News

@Database(entities = [News::class], version = 3, exportSchema = false)
@TypeConverters(NewsDto::class)
abstract class NewsDb: RoomDatabase() {

    abstract fun news_dao(): NewsDao
}