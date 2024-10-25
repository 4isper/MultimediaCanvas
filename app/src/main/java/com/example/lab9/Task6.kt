package com.example.lab9

import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun Task6(){
    val packageName = "com.example.lab9"
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VideoPlayer(videoUrl = "android.resource://${packageName}/${R.raw.bigbuckbunny}")
    }
}

@Composable
fun VideoPlayer(videoUrl: String) {
    AndroidView(factory = { context ->
        VideoView(context).apply {
            setVideoPath(videoUrl)
            val mediaController = MediaController(context)
            mediaController.setAnchorView(this)
            setMediaController(mediaController)
            start()
        }
    })
}