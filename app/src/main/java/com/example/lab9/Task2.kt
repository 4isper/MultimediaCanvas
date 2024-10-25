package com.example.lab9

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun Task2() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpriteAnimation()
    }
}

@Composable
fun SpriteAnimation() {
    val frameCount = 10
    val frameDuration = 45
    var currentFrame by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(frameDuration.toLong())
            currentFrame = (currentFrame + 1) % frameCount
        }
    }
    val context = LocalContext.current

    Canvas(modifier = Modifier.size(500.dp)) {
        val spriteId = getSpriteId(currentFrame)
        val bitmap = BitmapFactory.decodeResource(context.resources, spriteId)
        drawImage(image = bitmap.asImageBitmap())
    }
}

fun getSpriteId(index: Int): Int {
    return when (index) {
        0 -> R.drawable.run0
        1 -> R.drawable.run1
        2 -> R.drawable.run2
        3 -> R.drawable.run3
        4 -> R.drawable.run4
        5 -> R.drawable.run5
        6 -> R.drawable.run6
        7 -> R.drawable.run7
        8 -> R.drawable.run8
        else -> R.drawable.run9
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTask2() {
    Task2()
}