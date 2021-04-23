package com.caowj.fastdev

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.caowj.fastdev.view.activity.NewsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, NewsActivity::class.java))
    }
}