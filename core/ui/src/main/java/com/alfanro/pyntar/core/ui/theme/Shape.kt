package com.alfanro.pyntar.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// Shape Consistency Lock:
// We use a consistent rounded corner scale. 
// Small for inputs/chips (8.dp), Medium for cards (16.dp), Large for dialogs/bottom sheets (24.dp).
// Buttons will explicitly use CircleShape (pill) in their component definitions to differentiate interactive from structural.

val PyntarShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp)
)
