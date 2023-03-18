package com.example.cachinginandroid.di

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import coil.ImageLoader
import coil.request.CachePolicy
import com.example.cachinginandroid.api.DogsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(DogsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideDogsApi(retrofit: Retrofit): DogsApi =
        retrofit.create(DogsApi::class.java)
}