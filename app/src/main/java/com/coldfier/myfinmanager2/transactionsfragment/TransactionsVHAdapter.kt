package com.coldfier.myfinmanager2.transactionsfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coldfier.myfinmanager2.databinding.ItemTransactionBinding
import com.coldfier.myfinmanager2.models.Transaction

class TransactionsVHAdapter: RecyclerView.Adapter<TransactionsViewHolder>() {

    var transactionsList = mutableListOf<Transaction>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsViewHolder {
        return TransactionsViewHolder(
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TransactionsViewHolder, position: Int) {
        holder.bind(transactionsList[position])
    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }

}

class TransactionsViewHolder(private var binding: ItemTransactionBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(transaction: Transaction) {
        binding.transaction = transaction
    }
}