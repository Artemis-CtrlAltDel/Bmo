package com.example.bmo.pojo

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Source(
    @ColumnInfo(defaultValue = "Source Id is not available")
    val id: String? = "",
    @ColumnInfo(defaultValue = "Source Name is not available")
    val name: String
    ): Parcelable
