package com.example.serviceassignment


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import androidx.core.app.NotificationCompat


class ForegroundService : Service() {
    private val CHANNEL_ID: String = "foregroundServiceExample"
    private lateinit var mediaPlayer: MediaPlayer
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        val returnIntent = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            returnIntent,
            0
        )
        mediaPlayer.isLooping = true
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Notification")
            .setContentText("Music playing Service")
            .setSmallIcon(R.drawable.ic_baseline_music)
            .setContentIntent(pendingIntent)
            .build()
        mediaPlayer.start()
        startForeground(1, notification)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        stopSelf()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Example",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(serviceChannel)
        }
    }
}