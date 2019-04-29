package com.teamelrand.flashy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.teamelrand.flashy.data.Card
import com.teamelrand.flashy.data.CardDatabase

class MainActivity : AppCompatActivity() {
    lateinit var database: CardDatabase
    lateinit var data: List<Card>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = CardDatabase.getInstance(applicationContext)
        data = database.cardDao().getCards()
    }

    public fun onBtnPlay(view: View) {
        if (data.isEmpty()) {
            Toast.makeText(this, "Add a Card First", Toast.LENGTH_SHORT).show()
        } else {
            startActivity(Intent(this, PlayActivity::class.java))
        }
    }

    public fun onBtnViewCard(view: View) {
        if (data.isEmpty()) {
            Toast.makeText(this, "Add a Card First", Toast.LENGTH_SHORT).show()
        } else {
            startActivity(Intent(this, ViewCardActivity::class.java))
        }
    }

    public fun onBtnAddCard(view: View) {
        startActivity(Intent(this, AddCardActivity::class.java))
    }
}
