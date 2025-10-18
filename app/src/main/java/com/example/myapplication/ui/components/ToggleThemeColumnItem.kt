package com.example.myapplication.ui.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Blue30
import com.example.myapplication.ui.theme.Blue40

@Composable
fun ToggleThemeColumnItem(text: String, onCheckedChange: () -> Unit, isDark: Boolean) {
    Box(
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(61.dp)
                .clickable { onCheckedChange() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text,
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 16.dp)
            )
            Switch(
                modifier = Modifier.padding(end = 12.dp),
                checked = isDark,
                onCheckedChange = { onCheckedChange() },
                colors = SwitchDefaults.colors(
                    checkedBorderColor = Color.Transparent,
                    checkedTrackColor = Blue30,
                    checkedThumbColor = Blue40,
                    uncheckedBorderColor = Color.Transparent,
                    uncheckedThumbColor = Color(0xFFAEAFB4),
                )
            )
        }
    }
}
