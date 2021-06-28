package com.coldfier.myfinmanager2.transactionsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.coldfier.myfinmanager2.R
import com.coldfier.myfinmanager2.databinding.TransactionsFragmentBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class TransactionsFragment : Fragment() {

    private lateinit var viewModel: TransactionsViewModel
    private lateinit var binding: TransactionsFragmentBinding
    private var initCardId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arguments = TransactionsFragmentArgs.fromBundle(arguments)
        initCardId = arguments.currentCardId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.transactions_fragment, container, false)
        viewModel = ViewModelProvider(
            this,
            TransactionsVewModelFactory(initCardId, requireActivity().application)
        ).get(TransactionsViewModel::class.java)



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

        viewModel.cardsWithTransactionsList.observe(viewLifecycleOwner) {
            cardVHAdapter.contentList = it
        }

        viewModel.currentViewPagerPosition.observe(viewLifecycleOwner) {
            binding.cardHolderViewPager.setCurrentItem(it, false)
        }

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.logout_icon -> {
                    Firebase.auth.signOut()
                    try {
                        val googleSignInOptions =
                            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken(getString(R.string.def_web_client_id))
                                .requestEmail()
                                .build()
                        GoogleSignIn.getClient(requireContext(), googleSignInOptions).signOut()
                    } catch (e: Exception) {  }
                    findNavController().navigateUp()
                }
            }
            true
        }

        return binding.root
    }


}