package com.example.myapplication.creator

import com.example.myapplication.data.dto.TrackDto

class Storage {
    private val listTracks = listOf(
        TrackDto(
            trackName = "Владивосток 2000",
            artistName = "Мумий Троль",
            trackTimeMillis = 158000 // 2:38
        ),
        TrackDto(
            trackName = "Группа крови",
            artistName = "Кино",
            trackTimeMillis = 283000 // 4:43
        ),
        TrackDto(
            trackName = "Не смотри назад",
            artistName = "Ария",
            trackTimeMillis = 312000 // 5:12
        ),
        TrackDto(
            trackName = "Звезда по имени Солнце",
            artistName = "Кино",
            trackTimeMillis = 225000
        ),
        TrackDto(
            trackName = "Лондон",
            artistName = "Аквариум",
            trackTimeMillis = 272000
        ),
        TrackDto(
            trackName = "На заре",
            artistName = "Альянс",
            trackTimeMillis = 230000
        ),
        TrackDto(
            trackName = "Перемен",
            artistName = "Кино",
            trackTimeMillis = 296000
        ),
        TrackDto(
            trackName = "Розовый фламинго",
            artistName = "Сплин",
            trackTimeMillis = 195000
        ),
        TrackDto(
            trackName = "Танцевать",
            artistName = "Мельница",
            trackTimeMillis = 222000
        ),
        TrackDto(
            trackName = "Чёрный бумер",
            artistName = "Серега",
            trackTimeMillis = 241000
        )
    )

    fun search(request: String): List<TrackDto> {
        val normalizedRequest = request.normalize()
        val result = listTracks.filter {
            it.trackName.normalize().contains(normalizedRequest) ||
                    it.artistName.normalize().contains(normalizedRequest)
        }
        return result
    }

    private fun String.normalize(): String {
        val map = mapOf(
            'a' to 'а',
            'e' to 'е',
            'o' to 'о',
            'p' to 'р',
            'c' to 'с',
            'x' to 'х',
            'y' to 'у',
            'k' to 'к',
            'b' to 'в',
            'm' to 'м',
            't' to 'т',
            'h' to 'н',
            'l' to 'л'
        )
        return this.lowercase().map { map[it] ?: it }.joinToString("")
    }
}