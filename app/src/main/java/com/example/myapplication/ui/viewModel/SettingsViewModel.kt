package com.example.myapplication.ui.viewModel

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    fun onShare(context: Context) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "http://PlaylistMaker.com")
        context.startActivity(shareIntent)
    }

    fun onHelp(context: Context) {
        val helpIntent = Intent(Intent.ACTION_SENDTO)
        helpIntent.data = "mailto:".toUri()
        helpIntent.putExtra(Intent.EXTRA_EMAIL, "nsokolenko@sfedu.ru")
        helpIntent.putExtra(Intent.EXTRA_TITLE, "Сообщение разработчикам и разработчицам приложения Playlist Maker")
        helpIntent.putExtra(Intent.EXTRA_TEXT, "Спасибо разработчикам и разработчицам за крутое приложение!")
        context.startActivity(helpIntent)
    }

    fun onOffer(context: Context) {
        val offerIntent = Intent(Intent.ACTION_VIEW)
        offerIntent.data = "https://yandex.ru/legal/praktikum_offer/07022019/".toUri()
        context.startActivity(offerIntent)
    }

}