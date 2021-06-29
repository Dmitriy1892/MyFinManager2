package com.coldfier.myfinmanager2.cardsfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coldfier.myfinmanager2.databinding.ItemCardBinding
import com.coldfier.myfinmanager2.models.Card
import com.google.firebase.auth.FirebaseUser

class CardsRVAdapter(private val currentUser: FirebaseUser): ListAdapter<Card, CardsViewHolder>(CardsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        return CardsViewHolder(
            ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            currentUser
        )
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind(currentList[position])

    }

}

class CardsDiffCallback: DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.cardId == newItem.cardId
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }

}

class CardsViewHolder(private val binding: ItemCardBinding, private val currentUser: FirebaseUser): RecyclerView.ViewHolder(binding.root) {
    fun bind(card: Card) {
        binding.card = card
        binding.cardItem.setOnClickListener {
            val action = CardsFragmentDirections.actionCardsFragmentToTransactionsFragment(currentUser, card.cardId)
            it.findNavController().navigate(action)
        }
    }
}