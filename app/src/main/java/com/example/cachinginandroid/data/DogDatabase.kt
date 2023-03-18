package com.example.cachinginandroid.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Dog::class],
    version = 1
)
abstract class DogDatabase: RoomDatabase() {

    abstract val dogDao: DogDao

    companion object {
        const val DATABASE_NAME = "dogs_db"
    }
}