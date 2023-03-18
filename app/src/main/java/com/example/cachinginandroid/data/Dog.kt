package com.example.cachinginandroid.data

import android.graphics.Bitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dog(
    val uri: String,
    val timestamp: Long,
    @PrimaryKey val url: String
)