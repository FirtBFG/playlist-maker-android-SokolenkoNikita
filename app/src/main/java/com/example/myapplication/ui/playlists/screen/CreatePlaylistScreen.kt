package com.example.myapplication.ui.playlists.screen

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.components.PanelHeader
import com.example.myapplication.ui.playlists.viewModel.PlaylistsViewModel

@Composable
fun CreatePlaylistScreen(
    viewModel: PlaylistsViewModel,
    onBackClick: () -> Unit
) {
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


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            PanelHeader(
                title = stringResource(R.string.new_playlist),
                onClickAction = onBackClick
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 3.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 132.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
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
                Spacer(modifier = Modifier.height(138.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(
                            text = stringResource(R.string.name_with_star),
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
                            text = stringResource(R.string.description),
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
                        text = stringResource(R.string.save),
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

