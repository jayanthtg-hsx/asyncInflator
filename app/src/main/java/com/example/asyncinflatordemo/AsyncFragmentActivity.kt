package com.example.asyncinflatordemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AsyncFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PerfLogger.measured {
            setContentView(R.layout.activity_async_fragment)
        }
    }
}