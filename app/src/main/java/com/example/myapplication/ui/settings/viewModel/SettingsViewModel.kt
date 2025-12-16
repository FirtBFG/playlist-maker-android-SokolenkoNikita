package com.example.myapplication.ui.settings.viewModel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    fun onShare(context: Context, text: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        context.startActivity(shareIntent)
    }

    fun onHelp(context: Context, email: String, title: String, text: String) {
        val helpIntent = Intent(Intent.ACTION_SENDTO)
        helpIntent.data = "mailto:${email}".toUri()
        helpIntent.putExtra(Intent.EXTRA_TITLE, title)
        helpIntent.putExtra(Intent.EXTRA_TEXT, text)
        context.startActivity(helpIntent)
    }

    fun onOffer(context: Context, ref: Uri) {
        val offerIntent = Intent(Intent.ACTION_VIEW)
        offerIntent.data = ref
        context.startActivity(offerIntent)
    }

}