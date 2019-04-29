package com.teamelrand.flashy.data

import android.arch.persistence.room.*

@Dao
interface CardDao {

    @Query("SELECT * FROM cards")
    fun getCards(): List<Card>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg cards: Card)

    @Update
    fun edit(vararg cards: Card)

}