package com.example.cachinginandroid.presentation.cacheddogs

import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import android.util.LruCache
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import java.io.File

@Composable
fun CachedDogsScreen(
    viewModel: CachedDogsViewModel = hiltViewModel()
) {
    val dogs by viewModel.dogLiveData.observeAsState()
    val path = "${Environment.getExternalStorageDirectory()}/${Environment.DIRECTORY_PICTURES}"

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        LazyRow(
            modifier = Modifier
                .requiredWidth(300.dp)
                .requiredHeight(400.dp)
                .padding(start = 4.dp, end = 4.dp, top = 32.dp, bottom = 20.dp)
        ) {
            dogs?.forEach {
                item {
                    Image(
                        rememberAsyncImagePainter(model = File("$path/${it.uri}")),
                        contentDescription = "doggo image",
                        modifier = Modifier
                            .requiredWidth(300.dp)
                            .requiredHeight(400.dp)
                            .padding(start = 4.dp, end = 4.dp, top = 32.dp, bottom = 20.dp),
                        contentScale = ContentScale.Fit,
                    )
                }
            }
        }
        Button(
            onClick = { viewModel.clearAllDogs(dogs) },
              colors = ButtonDefaults.buttonColors(containerColor = Color(66,134,244))

        ) {
            Text(text = "clear dogs")
        }
    }
}





