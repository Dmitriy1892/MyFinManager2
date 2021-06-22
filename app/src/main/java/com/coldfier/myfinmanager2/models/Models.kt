package com.coldfier.myfinmanager2.models

data class Card(
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