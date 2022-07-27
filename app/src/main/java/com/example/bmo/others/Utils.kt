package com.example.bmo.others

import com.example.bmo.adapters.AllNewsAdapter
import com.example.bmo.pojo.Filter
import com.example.bmo.pojo.News
import com.example.bmo.viewmodel.NewsViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

val likes_formatter = LikesFormatter()
fun Long.format() = likes_formatter.format(this)

val time_formatter = TimeFormatter()
fun String.format() = time_formatter.format(this).joinToString(separator = " ")

fun Date.format(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun current_date_time(): Date = Calendar.getInstance().time

fun String.month() = this.split("T")[0].split("-")[1]
fun String.year() = this.split("T")[0].split("-")[0]

fun News.favorite_item(view_model: NewsViewModel) {

    when (is_favorite) {
        true -> view_model.insert_article(this)
        else -> view_model.delete_article(this.id)
    }

    favorite_count += if (is_favorite) -1 else 1
    is_favorite = !is_favorite
}

fun News.is_source_available(): Boolean =
    this.source.id!= null