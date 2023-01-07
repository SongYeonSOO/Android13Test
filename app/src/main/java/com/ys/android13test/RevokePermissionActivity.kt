package com.ys.android13test

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class RevokePermissionActivity : AppCompatActivity() {
    private val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        updatePermissionText()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revoke_permission)
        updatePermissionText()

        findViewById<Button>(R.id.btn_audio).setOnClickListener {
            launcher.launch(Manifest.permission.RECORD_AUDIO)
        }

        findViewById<Button>(R.id.btn).setOnClickListener {
            revokeSelfPermissionOnKill(Manifest.permission.RECORD_AUDIO)
            updatePermissionText()
        }
    }

    private fun updatePermissionText(){
        val granted = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
        findViewById<TextView>(R.id.text).text = "Is Audio Permission granted? ${granted}"
    }
}