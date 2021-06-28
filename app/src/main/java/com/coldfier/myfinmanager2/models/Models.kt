package com.coldfier.myfinmanager2.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "cards_table")
data class Card(
    @PrimaryKey(autoGenerate = true)
    var cardId: Long = 0L,

    var number: Int,

    var name: String,

    var balance: Double,

    var currency: String
)

@Entity(tableName = "transactions_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var transactionId: Long = 0L,

    val cardCreatorId: Long,

    var category: String,

    var location: String,

    var value: Double,

    var currency: String,

    var date: String
)

data class CardWithTransactions(
    @Embedded
    var card: Card,

    @Relation(parentColumn = "cardId", entityColumn = "cardCreatorId")
    var transactionsList: List<Transaction>
)