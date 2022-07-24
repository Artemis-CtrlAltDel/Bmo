package com.example.bmo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bmo.pojo.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert_article(article: News)

    @Query("DELETE FROM news_table WHERE id = :id")
    fun delete_article(id: Int)

    @Query("SELECT * FROM news_table")
    fun get_articles(): LiveData<List<News>>
}