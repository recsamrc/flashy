package com.teamelrand.flashy

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.teamelrand.flashy.data.Card
import com.teamelrand.flashy.data.CardDatabase
import kotlinx.android.synthetic.main.activity_play.*
import kotlinx.android.synthetic.main.card_back.*
import kotlinx.android.synthetic.main.card_front.*

class PlayActivity : AppCompatActivity() {
    lateinit var mSetRightOut: AnimatorSet
    lateinit var mSetRightIn: AnimatorSet
    var isBack = true

    lateinit var database: CardDatabase
    lateinit var data: List<Card>
    var currentCard = 0
    var right = 0
    var wrong = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        database = CardDatabase.getInstance(applicationContext)
        data = database.cardDao().getCards()

        loadCards()

        changeCameraDistance()
        loadAnimations()

        success.setOnClickListener {
            right++
            loadCards()
        }

        failure.setOnClickListener {
            wrong++
            loadCards()
        }

    }

    private fun loadCards() {
        if (currentCard == data.size) {
            intent = Intent(this, StatisticsActivity::class.java)
            intent.putExtra("right", right)
            intent.putExtra("wrong", wrong)
            startActivity(intent)
        } else {
            val card = data[currentCard]
            name_front.text = card.name
            name_back.text = card.name
            front_txt.text = card.frontText
            txt_back.text = card.backText
            currentCard++;
        }
    }

    private fun changeCameraDistance() {
        val distance = 8000;
        val scale = resources.displayMetrics.density * distance;
        play_cardFront.cameraDistance = scale
        play_cardBack.cameraDistance = scale
    }

    private fun loadAnimations() {
        mSetRightIn = AnimatorInflater.loadAnimator(this, R.animator.card_flip) as AnimatorSet
        mSetRightOut = AnimatorInflater.loadAnimator(this, R.animator.card_flip2) as AnimatorSet
    }

    public fun flipCard(view: View) {
        if (!isBack) {
            mSetRightOut.setTarget(play_cardFront)
            mSetRightIn.setTarget(play_cardBack)
            mSetRightOut.start()
            mSetRightIn.start()
            isBack = true
        } else {
            mSetRightOut.setTarget(play_cardBack)
            mSetRightIn.setTarget(play_cardFront)
            mSetRightOut.start()
            mSetRightIn.start()
            isBack = false
        }
    }
}
