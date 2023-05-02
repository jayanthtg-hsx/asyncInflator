package com.example.asyncinflatordemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class NavigatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigator)
        findViewById<View>(R.id.activityInflation).setOnClickListener { activityInflation() }
        findViewById<View>(R.id.fragmentInflation).setOnClickListener { fragmentInflation() }
    }

    private fun activityInflation() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun fragmentInflation() {
        startActivity(Intent(this, AsyncFragmentActivity::class.java))
    }
}