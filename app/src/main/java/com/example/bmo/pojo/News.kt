package com.example.bmo.pojo

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(tableName = "news_table")
@Parcelize
data class News(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(defaultValue = "Source is not available")
    val source: Source,
    @ColumnInfo(defaultValue = "Author is not available")
    val author: String = "Loading",
    @ColumnInfo(defaultValue = "Title is not available")
    val title: String = "Loading",
    @ColumnInfo(defaultValue = "Description is not available")
    val description: String,
    @ColumnInfo(defaultValue = "Url is not available")
    val url: String,
    @ColumnInfo(defaultValue = "UrlToImage is not available")
    val urlToImage: String = "Loading",
    @ColumnInfo(defaultValue = "PublishedAt is not available")
    val publishedAt: String = "Loading",
    @ColumnInfo(defaultValue = "Content is not available")
    val content: String,

    var is_favorite: Boolean = false,
    var favorite_count: Long = 0
) : Parcelable
