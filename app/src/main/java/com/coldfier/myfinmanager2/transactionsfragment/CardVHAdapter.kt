package com.coldfier.myfinmanager2.transactionsfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coldfier.myfinmanager2.databinding.ItemCardBinding
import com.coldfier.myfinmanager2.models.Card
import com.coldfier.myfinmanager2.models.Transaction

class CardVHAdapter(): RecyclerView.Adapter<CardViewHolder>() {

    var contentList = listOf(
        Card("1", 1111, "MTBank", 100.00, "BYN", listOf(
            Transaction("Food", "MINSK/TC GALLERY", "25", "BYN", "24.07.2021 14:37"),
            Transaction("Car", "MINSK/BELURUSNEFT", "55", "BYN", "25.07.2021 12:30"),
            Transaction("Food", "MINSK/TC GALLERY", "25", "BYN", "27.07.2021 14:37")
        )),

        Card("2", 2222, "BPS", 200.00, "EUR", listOf(
            Transaction("Food", "MINSK/MCDONALDS", "205", "BYN", "24.07.2021 14:37"),
            Transaction("Car", "MINSK/TC STOLICA", "75", "BYN", "25.07.2021 12:30"),
            Transaction("Food", "MINSK/GRUZIN.BY", "25", "BYN", "27.07.2021 14:37")
        )),

        Card("3", 3333, "Sber", 300.00, "USD", listOf(
            Transaction("Food", "MINSK/TC GALLERY", "25", "BYN", "24.07.2021 14:37"),
            Transaction("Car", "MINSK/BELURUSNEFT", "55", "BYN", "25.07.2021 12:30"),
            Transaction("Food", "MINSK/TC GALLERY", "25", "BYN", "27.07.2021 14:37")
        ))
    )
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
        holder.bind(contentList[position])
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