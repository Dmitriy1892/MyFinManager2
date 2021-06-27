package com.coldfier.myfinmanager2.cardsfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coldfier.myfinmanager2.databinding.ItemCardBinding
import com.coldfier.myfinmanager2.models.Card

class CardsRVAdapter: ListAdapter<Card, CardsViewHolder>(CardsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        return CardsViewHolder(
            ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind(currentList[position])

    }

}

class CardsDiffCallback: DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }

}

class CardsViewHolder(private val binding: ItemCardBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(card: Card) {
        binding.card = card
        binding.cardItem.setOnClickListener {
            // TODO: Need to navigate to TransactionsFragment with card in action value
        }
    }
}