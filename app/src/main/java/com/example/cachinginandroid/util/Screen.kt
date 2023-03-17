package com.example.cachinginandroid.util

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object GenerateDogsScreen: Screen("generate_dogs_screen")
    object CachedDogsScreen: Screen("cached_dogs_screen")
}