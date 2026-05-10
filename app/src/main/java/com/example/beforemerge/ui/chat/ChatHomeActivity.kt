package com.example.beforemerge.ui.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.beforemerge.R

class ChatHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_home)

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.chat_home_container, fragment)
            .commit()
    }
}