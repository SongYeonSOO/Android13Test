package com.ys.android13test

import android.app.LocaleManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateCurrentLocale()
        findViewById<Button>(R.id.btn).setOnClickListener {
            updateLocaleToJp()
            updateCurrentLocale()
        }
    }

    private fun updateCurrentLocale(){
        val localeManager = getSystemService(LocaleManager::class.java)
        var localeTags = localeManager.applicationLocales.toLanguageTags()
        if(localeTags.isNullOrEmpty()){
            localeTags = "System"
        }
        findViewById<TextView>(R.id.txt_current_locale).text = "applicationLocales:$localeTags"
    }

    private fun updateLocaleToJp(){
        val localeManager = getSystemService(LocaleManager::class.java)
        localeManager.applicationLocales = LocaleList.forLanguageTags("ja")
    }
}


