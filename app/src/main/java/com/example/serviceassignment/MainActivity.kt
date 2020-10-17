package com.example.serviceassignment

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        val bgServiceIntent = Intent(this, BackgroundService::class.java)
        val fgServiceIntent = Intent(this, ForegroundService::class.java)
        when (view.id) {
            R.id.start_fg_btn -> startService(fgServiceIntent)
            R.id.stop_fg_btn -> stopService(fgServiceIntent)
            R.id.start_btn -> startService(bgServiceIntent)
            R.id.stop_btn -> stopService(bgServiceIntent)
        }
    }

}

