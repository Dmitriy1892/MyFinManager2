package com.coldfier.myfinmanager2.transactionsfragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coldfier.myfinmanager2.databinding.ItemCardBinding
import com.coldfier.myfinmanager2.models.Card
import com.coldfier.myfinmanager2.models.CardWithTransactions

class CardVHAdapter: RecyclerView.Adapter<CardViewHolder>() {

    var contentList = listOf<CardWithTransactions>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(contentList[position].card)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }
}

class CardViewHolder(private val binding: ItemCardBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(card: Card) {
        binding.card = card
    }
}