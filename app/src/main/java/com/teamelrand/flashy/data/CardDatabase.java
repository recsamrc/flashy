package com.teamelrand.flashy.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Card.class}, version = 1)
public abstract class CardDatabase extends RoomDatabase {
    private static CardDatabase INSTANCE;

    public abstract CardDao cardDao();

    public static CardDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (CardDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context,
                            CardDatabase.class,
                            "cards_db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
