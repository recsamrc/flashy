package com.teamelrand.flashy.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teamelrand.flashy.R
import com.teamelrand.flashy.data.Card

class CardListAdapter(var context: Context, var cards: List<Card>, var front1: TextView, var back1: TextView, var front2: TextView, var back2: TextView) : RecyclerView.Adapter<CardListAdapter.ViewHolder>(), View.OnClickListener {
    override fun onClick(v: View?) {
        val pos = (v as View).tag as Int
        front1.text = cards[pos].name
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_card, p0, false))

    override fun getItemCount() = cards.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.title.text = cards[p1].name
        p0.note.text = cards[p1].frontText
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var title = view.findViewById<TextView>(R.id.item_title)
        var note = view.findViewById<TextView>(R.id.item_note)

        init {
            view.setOnClickListener {
                front1.text = title.text
                front2.text = note.text
            }

        }
    }
}