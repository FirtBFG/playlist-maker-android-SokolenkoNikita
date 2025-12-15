package com.example.myapplication.ui.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.YPLightGray
import com.example.myapplication.ui.theme.YPTextGray

@Composable
fun PanelSearch(
    searchText: String,
    onValueChange: (value: String) -> Unit,
    onTrailingIconClickAction: () -> Unit,
    onLendingIconClickAction: () -> Unit,
    modifier: Modifier = Modifier,
    onSearch: () -> Unit = onLendingIconClickAction
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .background(color = YPLightGray),
            value = searchText,
            onValueChange = {value -> onValueChange(value)},
            placeholder = {
                Text(
                    stringResource(id = R.string.search),
                    color = YPTextGray,
                    fontSize = 16.sp
                )
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        onLendingIconClickAction()
                    },
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = YPTextGray
                )
            },
            trailingIcon = {
                if (!searchText.isEmpty()) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = null,
                        modifier = Modifier.clickable(onClick = {onTrailingIconClickAction()}),
                        tint = YPTextGray
                    )
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = YPLightGray,
                unfocusedContainerColor = YPLightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch() })
        )
    }
}