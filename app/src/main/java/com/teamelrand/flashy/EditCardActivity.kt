package com.teamelrand.flashy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.teamelrand.flashy.data.Card
import kotlinx.android.synthetic.main.activity_edit_card.*

class EditCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_card)

        edit_title.setText(intent.getStringExtra("title"), TextView.BufferType.EDITABLE)
        edit_front.setText(intent.getStringExtra("front"), TextView.BufferType.EDITABLE)

        btnSave.setOnClickListener {
//            val card = Card()
            Toast.makeText(this, "In Progress", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnDelete.setOnClickListener {
            Toast.makeText(this, "In Progress", Toast.LENGTH_SHORT).show()
        }

    }
}
