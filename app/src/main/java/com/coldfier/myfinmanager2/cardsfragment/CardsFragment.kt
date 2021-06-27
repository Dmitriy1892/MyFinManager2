package com.coldfier.myfinmanager2.cardsfragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.coldfier.myfinmanager2.databinding.CardsFragmentBinding

class CardsFragment : Fragment() {

    private lateinit var binding: CardsFragmentBinding
    private lateinit var viewModel: CardsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = CardsFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, CardsViewModelFactory(requireActivity().application)).get(CardsViewModel::class.java)

        val adapter = CardsRVAdapter()
        binding.cardsRecyclerView.adapter = adapter
        binding.cardsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.addNewCardFAB.setOnClickListener {
            // TODO: Add logic to adding the new card
        }

        viewModel.cardsList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

}