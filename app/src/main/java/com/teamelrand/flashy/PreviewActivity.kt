package com.teamelrand.flashy

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.teamelrand.flashy.data.Card
import com.teamelrand.flashy.data.CardDatabase
import kotlinx.android.synthetic.main.activity_preview.*
import kotlinx.android.synthetic.main.card_back.*
import kotlinx.android.synthetic.main.card_front.*

class PreviewActivity : AppCompatActivity() {
    lateinit var database: CardDatabase

    lateinit var mSetRightOut: AnimatorSet
    lateinit var mSetRightIn: AnimatorSet

    var isBack = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        database = CardDatabase.getInstance(applicationContext);

        txt_back.text = intent.getStringExtra("back")
        front_txt.text = intent.getStringExtra("front")
        name_back.text = intent.getStringExtra("name")
        name_front.text = intent.getStringExtra("name")

        btn_createCard.setOnClickListener {
            database.cardDao().add(Card(
                0,
                intent.getStringExtra("name"),
                intent.getStringExtra("front"),
                intent.getStringExtra("back")
            ))
            startActivity(Intent(this, MainActivity::class.java))
        }

        changeCameraDistance()
        loadAnimations()
    }

    private fun changeCameraDistance() {
        val distance = 8000;
        val scale = resources.displayMetrics.density * distance;
        add_cardFront.cameraDistance = scale
        add_cardBack.cameraDistance = scale
    }

    private fun loadAnimations() {
        mSetRightIn = AnimatorInflater.loadAnimator(this, R.animator.card_flip) as AnimatorSet
        mSetRightOut = AnimatorInflater.loadAnimator(this, R.animator.card_flip2) as AnimatorSet
    }

    public fun flipCard(view: View) {
        if (!isBack) {
            mSetRightOut.setTarget(add_cardFront)
            mSetRightIn.setTarget(add_cardBack)
            mSetRightOut.start()
            mSetRightIn.start()
            isBack = true
        } else {
            mSetRightOut.setTarget(add_cardBack)
            mSetRightIn.setTarget(add_cardFront)
            mSetRightOut.start()
            mSetRightIn.start()
            isBack = false
        }
    }
}
