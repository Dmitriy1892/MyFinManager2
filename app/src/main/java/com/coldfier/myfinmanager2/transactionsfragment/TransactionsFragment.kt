package com.coldfier.myfinmanager2.transactionsfragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.coldfier.myfinmanager2.R
import com.coldfier.myfinmanager2.databinding.TransactionsFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class TransactionsFragment : Fragment() {

    private lateinit var viewModel: TransactionsViewModel
    private lateinit var binding: TransactionsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.transactions_fragment, container, false)

        val transactionsAdapter = TransactionsVHAdapter()
        val cardVHAdapter = CardVHAdapter()

        binding.cardHolderViewPager.adapter = cardVHAdapter
        binding.cardHolderViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                transactionsAdapter.transactionsList = cardVHAdapter.contentList[position].transactionsList.toMutableList()
            }
        })
        TabLayoutMediator(binding.scrollbarTabLayout, binding.cardHolderViewPager
        ) { _, _ -> }.attach()

        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
        linearLayoutManager.stackFromEnd = true
        binding.transactionsRecyclerView.adapter = transactionsAdapter
        binding.transactionsRecyclerView.layoutManager = linearLayoutManager

        viewModel = ViewModelProvider(this).get(TransactionsViewModel::class.java)

        return binding.root
    }
}