package com.example.myapplication.data.network

import com.example.myapplication.domain.NetworkClient
import com.example.myapplication.data.dto.TracksSearchRequest
import com.example.myapplication.data.dto.TracksSearchResponse
import com.example.myapplication.domain.api.TrackRepository
import com.example.myapplication.domain.models.Track
import kotlinx.coroutines.delay


class TrackRepositoryImpl (private val networkClient: NetworkClient) : TrackRepository {
    override suspend fun searchTrecks(expression: String): List<Track> {
        val response = networkClient.doRequest(TracksSearchRequest(expression))
        delay(1000)
        return if(response.resultCode == 200) {
            (response as TracksSearchResponse).results.map {
                val seconds = it.trackTimeMillis / 1000
                val minutes = seconds / 60
                Track(
                    artistName = it.artistName,
                    trackName = it.trackName,
                    trackTime = "%02d".format(minutes) + "%02d".format(seconds - minutes*60)
                )
            }
        } else {
            emptyList()
        }
    }

//    override suspend fun getALlTracks(): List<Track> {
//        delay(1000)
//        return listTracks
//    }
}


//val listTracks = listOf(
//    Track(
//        trackName = "Владивосток 2000",
//        artistName = "Мумий Троль",
//        trackTime = "2:38"
//    ),
//    Track(
//        trackName = "Группа крови",
//        artistName = "Кино",
//        trackTime = "4:43"
//    ),
//    Track(
//        trackName = "Не смотри назад",
//        artistName = "Ария",
//        trackTime = "5:12"
//    ),
//    Track(
//        trackName = "Звезда по имени Солнце",
//        artistName = "Кино",
//        trackTime = "3:45"
//    ),
//    Track(
//        trackName = "Лондон",
//        artistName = "Аквариум",
//        trackTime = "4:32"
//    ),
//    Track(
//        trackName = "На заре",
//        artistName = "Альянс",
//        trackTime = "3:50"
//    ),
//    Track(
//        trackName = "Перемен",
//        artistName = "Кино",
//        trackTime = "4:56"
//    ),
//    Track(
//        trackName = "Розовый фламинго",
//        artistName = "Сплин",
//        trackTime = "3:15"
//    ),
//    Track(
//        trackName = "Танцевать",
//        artistName = "Мельница",
//        trackTime = "3:42"
//    ),
//    Track(
//        trackName = "Чёрный бумер",
//        artistName = "Серега",
//        trackTime = "4:01"
//    )
//)