package com.example.cachinginandroid.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DogDao {

    @Query("SELECT * FROM dog order by timestamp desc")
    fun getDogs(): LiveData<List<Dog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dog: Dog)

    @Query("DELETE FROM dog")
    suspend fun clearDogs()
}