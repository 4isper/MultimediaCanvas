package com.example.lab9

import androidx.compose.ui.geometry.Offset

data class Particle(
    var position: Offset,
    var velocity: Offset,
    var radius: Float,
    var alpha: Float
)