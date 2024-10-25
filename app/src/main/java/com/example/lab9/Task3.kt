package com.example.lab9

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Task3() {
    ParticleScreen()
}

@Composable
fun ParticleScreen() {
    var particles by remember { mutableStateOf(listOf<Particle>()) }

    LaunchedEffect(particles) {
        while (particles.isNotEmpty()) {
            delay(16L)
            particles = particles.mapNotNull {
                val newAlpha = it.alpha - 0.01f
                if (newAlpha > 0f) {
                    it.copy(
                        position = it.position + it.velocity,
                        alpha = newAlpha
                    )
                } else {
                    null
                }
            }
        }
    }

    Canvas(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures { offset ->
                particles = particles + generateParticles(offset)
            }
        }) {

        drawRect(
            color = Color.Black,
            size = size
        )

        particles.forEach { particle ->
            drawParticle(particle)
        }
    }
}

fun DrawScope.drawParticle(particle: Particle) {
    drawCircle(
        color = Color.White.copy(alpha = particle.alpha),
        radius = particle.radius,
        center = particle.position
    )
}

fun generateParticles(center: Offset): List<Particle> {
    val numParticles = 29
    val particles = mutableListOf<Particle>()
    val speed = 4f

    for (i in 0 until numParticles) {
        val angle = (2 * Math.PI / numParticles * i).toFloat()
        val velocity = Offset(
            x = speed * cos(angle),
            y = speed * sin(angle)
        )
        val radius = 5f
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
