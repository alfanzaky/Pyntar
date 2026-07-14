package com.alfanro.pyntar.feature.auth.screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alfanro.pyntar.feature.auth.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToDashboard: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val sessionState by viewModel.sessionState.collectAsState()
    var isAnimated by remember { mutableStateOf(false) }
    var sessionChecked by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (isAnimated) 1f else 0f,
        animationSpec = tween(700, easing = FastOutSlowInEasing),
        label = "splash_alpha"
    )
    val scale by animateFloatAsState(
        targetValue = if (isAnimated) 1f else 0.75f,
        animationSpec = tween(700, easing = FastOutSlowInEasing),
        label = "splash_scale"
    )

    LaunchedEffect(Unit) {
        delay(100)
        isAnimated = true
        delay(1400)
        sessionChecked = true
    }

    LaunchedEffect(sessionChecked, sessionState) {
        if (sessionChecked && sessionState !is com.alfanro.pyntar.feature.auth.SessionState.Loading) {
            if (sessionState is com.alfanro.pyntar.feature.auth.SessionState.Active) {
                onNavigateToDashboard()
            } else {
                onNavigateToLogin()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .alpha(alpha)
                .scale(scale)
        ) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Pyntar Logo",
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Pyntar",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Kelola tugasmu, ukur produktivitasmu",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
