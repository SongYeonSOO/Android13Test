package com.ys.android13test

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.media.MediaRecorder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import java.io.File

class FGSTestAudioService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> {
                startForeground()
                startAACRecording()
            }
            ACTION_STOP -> {
                stopForeground(Service.STOP_FOREGROUND_REMOVE)
                stopSelf()
            }
        }
        return START_NOT_STICKY
    }

    private fun startForeground() {
        val notification = NotificationCompat.Builder(this, "CHANNEL_ID_TEST")
            .setSmallIcon(android.R.drawable.btn_star)
            .setContentTitle("Android 13 테스트입니다.")
            .setOnlyAlertOnce(true)
            .setAutoCancel(false)
            .build()

        startForeground(
            1,
            notification,
            ServiceInfo.FOREGROUND_SERVICE_TYPE_MICROPHONE
        )
    }

    private var recorder: MediaRecorder? = null
    private fun startAACRecording() {
        recorder = MediaRecorder(this@FGSTestAudioService).apply {
            try {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setOutputFile(getOutputFilePath())
                setAudioSamplingRate(44100)
                setAudioEncodingBitRate(64000)
                setAudioChannels(1)
            } catch (e: Exception) {
                e.printStackTrace()
                return
            }

            try {
                prepare()
                start()
            } catch(e: RuntimeException) {
                try {
                    prepare()
                    start()
                } catch (e: Exception) {
                    return
                }
            } catch (e: IllegalStateException) {
                e.printStackTrace()
                return
            }
        }
    }

    private fun stopAACRecording() {
        recorder?.run {
            try {
                stop()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            try {
                release()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getOutputFilePath(): String {
        File(filesDir.toString()).run {
            if (!exists()) {
                mkdir()
            }
            return "$path/test.aac"
        }
    }

    override fun onDestroy() {
        stopAACRecording()
        super.onDestroy()
    }

    companion object {
        val ACTION_START = "action_start"
        val ACTION_STOP = "action_stop"
    }
}