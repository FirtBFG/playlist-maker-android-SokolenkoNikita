package com.example.myapplication.ui.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuColumnItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .clickable { onClick() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row (
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Icon(
                    modifier = Modifier.padding(end = 8.dp),
                    imageVector = icon,
                    contentDescription = null,
                )
                Text(
                    text,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            Icon(
                modifier = Modifier.padding(end = 12.dp),
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}
