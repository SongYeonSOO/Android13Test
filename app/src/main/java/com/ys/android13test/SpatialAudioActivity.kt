package com.ys.android13test

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SpatialAudioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spatial_audio)
        findViewById<TextView>(R.id.txt).text = getSpatialAudioResult()
    }

    private fun getSpatialAudioResult(): String {
        val audioManager = getSystemService(AudioManager::class.java)
        val spatializer = audioManager.spatializer

        /*
        * public static final int SPATIALIZER_IMMERSIVE_LEVEL_MULTICHANNEL = 1;
        * public static final int SPATIALIZER_IMMERSIVE_LEVEL_NONE = 0;
        * public static final int SPATIALIZER_IMMERSIVE_LEVEL_OTHER = -1;
        * */
        return "${spatializer.immersiveAudioLevel}, isEnabled=${spatializer.isEnabled}, ${spatializer.canBeSpatialized(getAudioAttributes(), getAudioFormat())}"
    }

    private fun getAudioAttributes(): AudioAttributes {
        return AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .build()
    }

    private fun getAudioFormat(): AudioFormat {
        return AudioFormat.Builder()
            .setEncoding(AudioFormat.ENCODING_MP3)
            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
            .setSampleRate(44100)
            .setChannelIndexMask(1)
            .build()
    }
}