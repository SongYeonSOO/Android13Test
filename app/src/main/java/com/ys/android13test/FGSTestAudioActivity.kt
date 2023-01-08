package com.ys.android13test

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class FGSTestAudioActivity : AppCompatActivity() {
    private val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        //do nothing.
    }
    private val notificationLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        createNotificationChannel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fgstest_audio)

        findViewById<Button>(R.id.btn_permission).setOnClickListener {
            launcher.launch(Manifest.permission.RECORD_AUDIO)
        }

        findViewById<Button>(R.id.btn).setOnClickListener {
            startService()
        }

        val permissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED

        if(permissionGranted){
            createNotificationChannel()
        } else {
            notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }


    private fun createNotificationChannel() {
        NotificationChannel("CHANNEL_ID_TEST", "CHANNEL", NotificationManager.IMPORTANCE_HIGH)
            .apply {
                enableVibration(false)
                setShowBadge(false)
                lockscreenVisibility = Notification.VISIBILITY_PRIVATE
                setSound(null, null)
            }.run {
                val manager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
                manager?.createNotificationChannel(this)

                android.util.Log.d("test-ys-","manager?.notificationChannels=${manager?.notificationChannels}")
            }
    }

    private fun startService() {
        val permissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
        if (!permissionGranted) {
            android.util.Log.d("test-ys-","permission not granted")
            return
        }

        startService(Intent(this@FGSTestAudioActivity, FGSTestAudioService::class.java).apply {
            action = FGSTestAudioService.ACTION_START
        })
    }
}