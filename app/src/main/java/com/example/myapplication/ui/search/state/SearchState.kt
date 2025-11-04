package com.example.myapplication.ui.search.state

import com.example.myapplication.domain.models.Track

sealed class SearchState {
    object Initial: SearchState() // Cостояние экрана при первой загрузке
    object Searching: SearchState() // Cостояние экрана при начале поиска
    object EmptyList: SearchState() // Сщстояние экрана рпи пустом списке треков
    data class Success(val foundList: List<Track>): SearchState() // Cостояние экрана при успешном завершении поиска
    data class Error(val error: String): SearchState() // Cостояние экрана если при запросе к серверу произошла ошибка
}