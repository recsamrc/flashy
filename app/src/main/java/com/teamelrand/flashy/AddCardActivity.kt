package com.teamelrand.flashy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_card.*

class AddCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        btnPreview.setOnClickListener {
            val intent = Intent(this, PreviewActivity::class.java)
            intent.putExtra("name",ed_title.text.toString())
            intent.putExtra("front",ed_front.text.toString())
            intent.putExtra("back",ed_back.text.toString())
            startActivity(intent)
        }

    }


}
