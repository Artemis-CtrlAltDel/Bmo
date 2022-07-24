package com.example.bmo.di

import android.app.Application
import androidx.room.Room
import com.example.bmo.db.NewsDao
import com.example.bmo.db.NewsDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provide_db(application: Application): NewsDb =
            Room.databaseBuilder(application, NewsDb::class.java, "news_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

        @JvmStatic
        @Provides
        @Singleton
        fun provide_dao(news_db: NewsDb): NewsDao =
            news_db.news_dao()

    }
}