package com.example.asyncinflatordemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.asynclayoutinflater.view.AsyncLayoutInflater

class MainActivity : AppCompatActivity() {

    private val lazyInflater by lazy { AsyncLayoutInflater(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PerfLogger.measured {
            setContentView(R.layout.activity_main)
            lazyInflater.inflate(R.layout.text_views, findViewById(R.id.container))
            { view, res, parent -> parent!!.addView(view) }
        }
    }
}