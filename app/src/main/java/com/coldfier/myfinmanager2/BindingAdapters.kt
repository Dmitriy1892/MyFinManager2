package com.coldfier.myfinmanager2

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.coldfier.myfinmanager2.models.Card
import com.coldfier.myfinmanager2.models.Transaction

@SuppressLint("SetTextI18n")
@BindingAdapter("bindBalance")
fun bindBalance(textView: TextView, card: Card) {
    textView.text = "${card.balance} ${card.currency}"
}

@BindingAdapter("bindCardNumber")
fun bindCardNumber(textView: TextView, card: Card) {
    textView.text = card.number.toString()
}

@SuppressLint("SetTextI18n")
@BindingAdapter("bindTransactionValue")
fun bindTransactionValue(textView: TextView, transaction: Transaction) {
    textView.text = "-${transaction.value} ${transaction.currency}"
}