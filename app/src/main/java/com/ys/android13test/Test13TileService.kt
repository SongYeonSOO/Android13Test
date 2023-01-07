package com.ys.android13test

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService


class Test13TileService: TileService() {
    override fun onClick() {
        super.onClick()
        val tile = qsTile // get Instance.

        when (tile.state) {
            Tile.STATE_ACTIVE -> tile.state = Tile.STATE_INACTIVE
            Tile.STATE_INACTIVE -> tile.state = Tile.STATE_ACTIVE
            else -> throw IllegalStateException("Unexpected value: $tile")
        }
        android.util.Log.d("test-ys-", "onClick: tile.state: ${tile.state}")
        tile.updateTile()
    }

    override fun onTileAdded() {
        super.onTileAdded()
        android.util.Log.d("test-ys-","onTileAdded")
    }

    override fun onTileRemoved() {
        super.onTileRemoved()
        android.util.Log.d("test-ys-","onTileRemoved")
    }

    override fun onStartListening() {
        super.onStartListening()
        android.util.Log.d("test-ys-","onStartListening")
    }

    override fun onStopListening() {
        super.onStopListening()
        android.util.Log.d("test-ys-","onStopListening")
    }
}