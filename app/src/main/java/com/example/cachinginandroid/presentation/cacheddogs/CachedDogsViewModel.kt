package com.example.cachinginandroid.presentation.cacheddogs

import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cachinginandroid.data.Dog
import com.example.cachinginandroid.data.DogDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CachedDogsViewModel @Inject constructor(private val dogDao: DogDao) : ViewModel() {
    private val path = "${Environment.getExternalStorageDirectory()}/${Environment.DIRECTORY_PICTURES}"

    fun clearAllDogs(dogs: List<Dog>?) {
        viewModelScope.launch {
            dogs?.forEach{
                File("$path/${it.uri}").delete()
            }
            dogDao.clearDogs()
        }
    }
    val dogLiveData = dogDao.getDogs()
}