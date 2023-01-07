package com.ys.android13test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_locale).setOnClickListener {
            startActivity(Intent(this, LocaleActivity::class.java))
        }

        findViewById<Button>(R.id.btn_hyphen).setOnClickListener {
            startActivity(Intent(this, HyphenActivity::class.java))
        }

        findViewById<Button>(R.id.btn_line_break).setOnClickListener {
            startActivity(Intent(this, LineBreakWordStyleActivity::class.java))
        }

    }

}


