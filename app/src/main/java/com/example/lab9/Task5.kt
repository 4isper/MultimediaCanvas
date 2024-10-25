package com.example.lab9

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource

@Composable
fun Task5(modifier: Modifier = Modifier) {
    var isPlaying by remember { mutableStateOf(false) }
    var currentTrackIndex by remember { mutableStateOf(0) }
    val trackList = listOf(R.raw.eminemtrack1, R.raw.eminemtrack2, R.raw.eminemtrack3)
    val artworkList = listOf(R.drawable.artwork1, R.drawable.artwork2, R.drawable.artwork3)
    val trackTitles = listOf(
        "Eminem - Love The Way You Lie ft. Rihanna",
        "Eminem - Mockingbird",
        "Eminem - The Real Slim Shady"
    )
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var currentPosition by remember { mutableStateOf(0) }
    val context = LocalContext.current

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    LaunchedEffect(currentTrackIndex) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, trackList[currentTrackIndex]).apply {
            setOnCompletionListener {
                currentTrackIndex = (currentTrackIndex + 1) % trackList.size
            }
            seekTo(currentPosition)
            if (isPlaying) start()
        }
    }

    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            mediaPlayer?.start()
        } else {
            mediaPlayer?.pause()
            currentPosition = mediaPlayer?.currentPosition ?: 0
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(artworkList[currentTrackIndex]),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 4.dp)
                .size(300.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        Text(
            trackTitles[currentTrackIndex],
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 15.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    currentPosition = 0
                    currentTrackIndex = (currentTrackIndex - 1 + trackList.size) % trackList.size
                },
                modifier = Modifier.padding(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
            }

            Button(
                onClick = {
                    isPlaying = !isPlaying
                },
                modifier = Modifier.padding(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Icon(
                    if (isPlaying) ImageVector.vectorResource(id = R.drawable.baseline_pause_24)
                    else Icons.Default.PlayArrow,
                    contentDescription = if (isPlaying) "Pause" else "Play",
                    modifier = Modifier.size(65.dp),
                    tint = Color.White
                )
            }

            Button(
                onClick = {
                    currentPosition = 0
                    currentTrackIndex = (currentTrackIndex + 1) % trackList.size
                },
                modifier = Modifier.padding(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
            }
        }
    }
}

