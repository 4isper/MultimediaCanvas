package com.example.lab9

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.Path

@Composable
fun Task1(modifier: Modifier) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(
            color = Color.Cyan,
            topLeft = Offset(0f, 0f),
            size = size
        )

        drawRect(
            color = Color.Green,
            topLeft = Offset(0f, size.height * 0.65f),
            size = Size(size.width, size.height * 0.35f)
        )

        drawHouse()
    }
}

fun DrawScope.drawHouse() {
    drawRect(
        color = Color(0xFFF4A261),
        topLeft = Offset(size.width * 0.3f, size.height * 0.5f),
        size = Size(size.width * 0.4f, size.height * 0.2f)
    )

    val roofPath = Path().apply {
        moveTo(size.width * 0.2f, size.height * 0.5f)
        lineTo(size.width * 0.5f, size.height * 0.35f)
        lineTo(size.width * 0.8f, size.height * 0.5f)
        close()
    }

    drawRect(
        color = Color.Red,
        topLeft = Offset(size.width * 0.6f, size.height * 0.33f),
        size = Size(size.width * 0.05f, size.height * 0.1f)
    )

    drawPath(
        path = roofPath,
        color = Color(0xFF8D5524)
    )
}