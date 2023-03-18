package com.example.cachinginandroid.presentation.generatedogs

import android.graphics.Bitmap
import android.util.LruCache
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun GenerateDogsScreen(
    navController: NavController,
    cache: LruCache<Long, Bitmap>,
    viewModel: GenerateDogsViewModel = hiltViewModel()
) {
    val url by viewModel.urlLiveData.observeAsState()
    val bitmap by viewModel.bitmapLiveData.observeAsState()

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val imageLoader = ImageLoader.Builder(context)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .build()


    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = bitmap,
            contentDescription = "doggo image",
            modifier = Modifier
                .requiredWidth(300.dp)
                .requiredHeight(400.dp)
                .padding(start = 4.dp, end = 4.dp, top = 32.dp, bottom = 20.dp),
            contentScale = ContentScale.Fit,
            onSuccess = {
                cache.put(System.currentTimeMillis(), bitmap)
            }
        )
        Button(onClick = { viewModel.makeApiCall(ImageRequest.Builder(context), imageLoader) }) {
            Text(text = "Generate dog")
        }
    }

}
