package com.example.beforemerge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.beforemerge.ui.chat.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, HomeFragment())
                .commit()
        }
    }
}