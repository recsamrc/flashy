package com.teamelrand.flashy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_statistics.*

class StatisticsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        val text1 = "You have got " + intent.getIntExtra("right", 0) + " questions right"
        val text2 = "And " + intent.getIntExtra("wrong", 0) + " wrong."
        txt_success.text = text1
        txt_failure.text = text2

        gotoMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
