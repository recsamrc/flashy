package com.teamelrand.flashy.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "cards")
data class Card (
    @PrimaryKey(autoGenerate = true) var uid: Int,
    var name: String,
    @ColumnInfo(name = "front_text")var frontText: String,
    @ColumnInfo(name = "back_text")var backText: String
)