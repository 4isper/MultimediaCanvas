package com.example.lab9

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random


@Composable
fun Task4(){
    ParticleFountainScreen()
}
@Composable
fun ParticleFountainScreen() {
    var particles by remember { mutableStateOf(listOf<Particle>()) }

    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val density = LocalDensity.current
    val screenWidthPx = with(density) { screenWidth.toPx() }
    val screenHeightPx = with(density) { screenHeight.toPx() }

    LaunchedEffect(Unit) {
        while (true) {
            particles = particles + generateFountainParticles(
                Offset(screenWidthPx/2, screenHeightPx)
            )
            delay(100L)
        }
    }

    LaunchedEffect(particles) {
        while (particles.isNotEmpty()) {
            delay(16L)
            particles = particles.mapNotNull {
                val newAlpha = it.alpha - 0.005f
                val newPosition = it.position + it.velocity
                val newVelocity = Offset(it.velocity.x, it.velocity.y + 0.05f)
                if (newAlpha > 0f && newPosition.y < screenHeightPx) {
                    it.copy(
                        position = newPosition,
                        velocity = newVelocity,
                        alpha = newAlpha
                    )
                } else {
                    null
                }
            }
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        // Чёрный фон
        drawRect(
            color = Color.Black,
            size = size
        )

        // Рисуем частицы
        particles.forEach { particle ->
            drawParticle(particle)
        }
    }
}

fun generateFountainParticles(center: Offset): List<Particle> {
    val numParticles = 20
    val particles = mutableListOf<Particle>()
    val speedRange = 6f..10f
    val angleRange = 60..120

    for (i in 0 until numParticles) {
        val angle = Random.nextInt(angleRange.first, angleRange.last + 1).toFloat() * (Math.PI / 180).toFloat()
        val speed = Random.nextFloat() * (speedRange.endInclusive - speedRange.start) + speedRange.start
        val velocity = Offset(
            x = speed * cos(angle),
            y = -speed * sin(angle)
        )
        val radius = Random.nextFloat() * 8f + 2f
        val alpha = 1f

        particles.add(
            Particle(
                position = center,
                velocity = velocity,
                radius = radius,
                alpha = alpha
            )
        )
    }
    return particles
}

