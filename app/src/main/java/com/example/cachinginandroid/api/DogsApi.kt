package com.example.cachinginandroid.api

import retrofit2.http.GET

interface DogsApi {

    companion object {
        const val BASE_URL = "https://dog.ceo/api/breeds/image/"
    }

    @GET("random")
    suspend fun getRandomDogUrl(): DogApiResponse
}