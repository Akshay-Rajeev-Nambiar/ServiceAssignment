package com.example.serviceassignment

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

class BackgroundService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}