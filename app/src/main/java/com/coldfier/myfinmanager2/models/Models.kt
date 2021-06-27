package com.coldfier.myfinmanager2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CardsCollection(
    var cardList: List<Card>
)

@Entity(tableName = "cards_table")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val id: String,

    var number: Int,

    var name: String,

    var balance: Double,

    var currency: String,

    var transactionsList: List<Transaction> = listOf()
)

data class Transaction(
    var category: String,
    var location: String,
    var value: String,
    var currency: String,
    var date: String,
)