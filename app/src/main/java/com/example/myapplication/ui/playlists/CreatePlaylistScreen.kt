package com.example.myapplication.ui.playlists

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.example.myapplication.R

@Composable
fun CreatePlaylistScreen(
    viewModel: PlaylistsViewModel,
    onBackClick: () -> Unit
) {
    val headerHeight = 80.dp
    val cornerRadius = 16.dp
    var name by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    
    val coverImageUri by viewModel.coverImageUri.collectAsState()
    val context = LocalContext.current
    
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.setCoverImageUri(it.toString())
        }
    }
    
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            imagePickerLauncher.launch("image/*")
        }
    }
    
    val isNameFilled = name.isNotBlank()
    val buttonColor = if (isNameFilled) Color(0xFF3772E7) else Color(0xFFAEAFB4)
    val borderColor = if (isNameFilled) Color(0xFF3772E7) else Color.Gray
    val descBorderColor = if (desc.isNotBlank()) Color(0xFF3772E7) else Color.Gray

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight + cornerRadius)
                .background(color = Color.White)
                .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Назад",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = onBackClick)
                )
                Spacer(modifier = Modifier.width(28.dp))
                Text(
                    text = "Новый плейлист",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W500
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = headerHeight + 3.dp)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Обложка:",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .size(205.dp)
                        .clickable {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                imagePickerLauncher.launch("image/*")
                            } else {
                                when {
                                    ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.READ_EXTERNAL_STORAGE
                                    ) == PackageManager.PERMISSION_GRANTED -> {
                                        imagePickerLauncher.launch("image/*")
                                    }
                                    else -> {
                                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                                    }
                                }
                            }
                        }
                ) {
                    if (coverImageUri != null) {
                        AsyncImage(
                            model = Uri.parse(coverImageUri),
                            contentDescription = stringResource(R.string.playlist_cover),
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.ic_music),
                            contentDescription = stringResource(R.string.add_cover),
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { 
                        Text(
                            text = "Название*",
                            fontSize = 16.sp
                        ) 
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = if (name.isBlank()) Color.Gray else borderColor,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color(0xFFB5B5B6),
                        unfocusedLabelColor = Color(0xFFB5B5B6),
                        cursorColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = desc,
                    onValueChange = { desc = it },
                    label = { 
                        Text(
                            text = "Описание",
                            fontSize = 16.sp
                        ) 
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = descBorderColor,
                        unfocusedBorderColor = if (desc.isBlank()) Color.Gray else descBorderColor,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color(0xFFB5B5B6),
                        unfocusedLabelColor = Color(0xFFB5B5B6),
                        cursorColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                Button(
                    onClick = {
                        if (name.isNotBlank()) {
                            viewModel.createNewPlayList(name, description = desc)
                            onBackClick()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .padding(horizontal = 17.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    ),
                    shape = RoundedCornerShape(8.dp),
                    enabled = isNameFilled
                ) {
                    Text(
                        text = "Сохранить",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

