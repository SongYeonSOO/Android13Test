package com.ys.android13test

import android.app.StatusBarManager
import android.content.ComponentName
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.concurrent.Executor

class TileServiceActivtiy : AppCompatActivity() {
    private val executor = Executor {
        android.util.Log.d("test-ys-", "executor executed.")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tile_service)

        findViewById<Button>(R.id.btn).setOnClickListener {
            requestAddingTile()
        }
    }

    private fun requestAddingTile() {
        val statusBarManager = getSystemService(StatusBarManager::class.java)
        statusBarManager.requestAddTileService(
            ComponentName(this@TileServiceActivtiy, Test13TileService::class.java),
            "android13Tile",
            Icon.createWithResource(this, android.R.drawable.btn_star),
            executor
        ) { value ->
            android.util.Log.d("test-ys-", "value=${value}")
        }
    }
}



