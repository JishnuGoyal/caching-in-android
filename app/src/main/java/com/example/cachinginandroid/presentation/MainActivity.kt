package com.example.cachinginandroid.presentation

import android.graphics.Bitmap
import android.os.Bundle
import android.util.LruCache
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cachinginandroid.presentation.cacheddogs.CachedDogsScreen
import com.example.cachinginandroid.presentation.generatedogs.GenerateDogsScreen
import com.example.cachinginandroid.presentation.home.HomeScreen
import com.example.cachinginandroid.ui.theme.CachingInAndroidTheme
import com.example.cachinginandroid.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var memoryCache: LruCache<Long, Bitmap>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupCache()
        setContent {
            CachingInAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            HomeScreen(navController = navController)
                        }
                        composable(route = Screen.GenerateDogsScreen.route) {
                            GenerateDogsScreen(navController = navController, memoryCache)
                        }
                        composable(route = Screen.CachedDogsScreen.route){
                            CachedDogsScreen(navController = navController, memoryCache)
                        }
                    }
                }
            }
        }
    }

    private fun setupCache(){
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

        // Use 1/8th of the available memory for this memory cache.
        val cacheSize = maxMemory / 8

        memoryCache = object : LruCache<Long, Bitmap>(cacheSize) {

            override fun sizeOf(key: Long, bitmap: Bitmap): Int {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.byteCount / 1024
            }
        }
    }


}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CachingInAndroidTheme {
        Greeting("Android")
    }
}