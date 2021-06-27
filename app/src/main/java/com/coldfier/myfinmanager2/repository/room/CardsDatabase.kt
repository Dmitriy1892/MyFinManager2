package com.coldfier.myfinmanager2.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.coldfier.myfinmanager2.models.Card

@Database(entities = [Card::class], version = 1, exportSchema = false)
abstract class CardsDatabase: RoomDatabase() {

    abstract val cardsDao: CardsDao

    companion object {

        @Volatile
        private var INSTANCE: CardsDatabase? = null

        fun getInstance(context: Context): CardsDatabase {
            return synchronized(this) {
                INSTANCE ?: kotlin.run {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        CardsDatabase::class.java,
                        "cards_database"
                    ).build()
                    INSTANCE!!
                }
            }
        }
    }
}