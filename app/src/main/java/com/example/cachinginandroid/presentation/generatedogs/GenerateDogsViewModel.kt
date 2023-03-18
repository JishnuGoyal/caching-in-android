package com.example.cachinginandroid.presentation.generatedogs

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.util.LruCache
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.cachinginandroid.api.DogsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateDogsViewModel @Inject constructor(
    private val dogsApi: DogsApi
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

}