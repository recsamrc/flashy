package com.teamelrand.flashy

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.teamelrand.flashy.adapters.CardListAdapter
import com.teamelrand.flashy.data.Card
import com.teamelrand.flashy.data.CardDatabase
import kotlinx.android.synthetic.main.activity_view_card.*
import kotlinx.android.synthetic.main.card_back.*
import kotlinx.android.synthetic.main.card_front.*

class ViewCardActivity : AppCompatActivity() {
    lateinit var mSetRightOut: AnimatorSet
    lateinit var mSetRightIn: AnimatorSet
    var isBack = true

    lateinit var database: CardDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_card)

        database = CardDatabase.getInstance(applicationContext)
        val cards = database.cardDao().getCards()
        cardList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        cardList.adapter = CardListAdapter(this, cards, name_front, name_back, front_txt, txt_back)

        btnEditCard.setOnClickListener {
            val intent = Intent(this, EditCardActivity::class.java)
            intent.putExtra("title", name_front.text.toString())
            intent.putExtra("front", front_txt.text.toString())
            startActivity(intent)
        }

        loadAnimations();
        changeCameraDistance();
    }

    private fun changeCameraDistance() {
        val distance = 8000;
        val scale = resources.displayMetrics.density * distance;
        view_cardFront.cameraDistance = scale
        view_cardBack.cameraDistance = scale
    }

    private fun loadAnimations() {
        mSetRightIn = AnimatorInflater.loadAnimator(this, R.animator.card_flip) as AnimatorSet
        mSetRightOut = AnimatorInflater.loadAnimator(this, R.animator.card_flip2) as AnimatorSet
    }

    public fun flipCard(view: View) {
        if (!isBack) {
            mSetRightOut.setTarget(view_cardFront)
            mSetRightIn.setTarget(view_cardBack)
            mSetRightOut.start()
            mSetRightIn.start()
            textView.text = "Front"
            isBack = true
        } else {
            mSetRightOut.setTarget(view_cardBack)
            mSetRightIn.setTarget(view_cardFront)
            mSetRightOut.start()
            mSetRightIn.start()
            textView.text = "Back"
            isBack = false
        }
    }
}
