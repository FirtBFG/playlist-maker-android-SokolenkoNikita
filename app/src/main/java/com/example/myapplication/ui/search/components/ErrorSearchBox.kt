package com.example.myapplication.ui.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun ErrorSearchBox(error: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 120.dp)
                    .padding(top = 102.dp)
                    .size(120.dp),
                painter = painterResource(R.drawable.ic_no_connection),
                contentDescription = stringResource(R.string.no_connection_desc)
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = stringResource(R.string.error),
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = error,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
            )
        }
    }
}