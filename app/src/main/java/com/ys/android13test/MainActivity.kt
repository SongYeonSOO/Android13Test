package com.ys.android13test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback

class MainActivity : AppCompatActivity() {
    private val onBackPressedCallback = OnBackPressedCallbackChild(true)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        onBackInvokedDispatcher.registerOnBackInvokedCallback(OnBackInvokedDispatcher.PRIORITY_DEFAULT, onBackInvokedCallback)
    }

    private val onBackInvokedCallback: ()-> Unit = {
        //조건에 따라 등록
        onBackPressedDispatcher.addCallback(onBackPressedCallback)
        android.util.Log.d("test-ys-","when onBackInvokedCalled.")
    }

    override fun onDestroy() {
        onBackInvokedDispatcher.unregisterOnBackInvokedCallback(onBackInvokedCallback)
        super.onDestroy()
    }

    inner class OnBackPressedCallbackChild(enabled: Boolean): OnBackPressedCallback(enabled) {
        override fun handleOnBackPressed() {
            android.util.Log.d("test-ys-","handleOnBackPressed")
            finish()
        }
    }

    private fun initView(){
        findViewById<Button>(R.id.btn_locale).setOnClickListener {
            startActivity(Intent(this, LocaleActivity::class.java))
        }

        findViewById<Button>(R.id.btn_hyphen).setOnClickListener {
            startActivity(Intent(this, HyphenActivity::class.java))
        }

        findViewById<Button>(R.id.btn_line_break).setOnClickListener {
            startActivity(Intent(this, LineBreakWordStyleActivity::class.java))
        }
        findViewById<Button>(R.id.btn_tile_service).setOnClickListener {
            startActivity(Intent(this, TileServiceActivtiy::class.java))
        }

        findViewById<Button>(R.id.btn_revoke_permission).setOnClickListener {
            startActivity(Intent(this, RevokePermissionActivity::class.java))
        }
        findViewById<Button>(R.id.btn_spatial_audio).setOnClickListener {
            startActivity(Intent(this, SpatialAudioActivity::class.java))
        }

        findViewById<Button>(R.id.btn_fgs_test).setOnClickListener {
            startActivity(Intent(this, FGSTestAudioActivity::class.java))
        }
    }

}


