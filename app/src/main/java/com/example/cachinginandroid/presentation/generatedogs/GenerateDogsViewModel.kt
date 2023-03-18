package com.example.cachinginandroid.presentation.generatedogs

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.cachinginandroid.api.DogsApi
import com.example.cachinginandroid.data.Dog
import com.example.cachinginandroid.data.DogDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateDogsViewModel @Inject constructor(
    private val dogsApi: DogsApi,
    private val dogDao: DogDao
) : ViewModel() {

    private val _urlLiveData = MutableLiveData<String?>(null)
    val urlLiveData get() = _urlLiveData

    private val _bitmapLiveData = MutableLiveData<Bitmap?>(null)
    val bitmapLiveData get() = _bitmapLiveData

    fun makeApiCall(imageRequestBuilder: ImageRequest.Builder, loader: ImageLoader) {
        viewModelScope.launch {
            val dogApiResponse = dogsApi.getRandomDogUrl()
            Log.d("dog api", "$dogApiResponse")
            if (dogApiResponse.status == "success") {
                _urlLiveData.value = dogApiResponse.message

                val request = imageRequestBuilder
                    .data(_urlLiveData.value)
                    .allowHardware(false) // Disable hardware bitmaps.
                    .build()

                val result = (loader.execute(request) as SuccessResult).drawable
                _bitmapLiveData.value = (result as BitmapDrawable).bitmap

            }
        }
    }
    fun updateDatabase(time: Long, filename: String) {
      viewModelScope.launch {
          dogDao.insertDog(Dog(filename, time, urlLiveData.value!!))
      }
    }

}