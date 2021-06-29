package com.coldfier.myfinmanager2.cardsfragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.coldfier.myfinmanager2.databinding.CardsFragmentBinding
import com.google.firebase.auth.FirebaseUser

class CardsFragment : Fragment() {

    private lateinit var binding: CardsFragmentBinding
    private lateinit var viewModel: CardsViewModel
    private lateinit var currentUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arguments = CardsFragmentArgs.fromBundle(requireArguments())
        currentUser = arguments.currentUser
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = CardsFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, CardsViewModelFactory(requireActivity().application)).get(CardsViewModel::class.java)

        val adapter = CardsRVAdapter(currentUser)
        binding.cardsRecyclerView.adapter = adapter
        binding.cardsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.addNewCardFAB.setOnClickListener {
            val action = CardsFragmentDirections.actionCardsFragmentToNewCardFragment(currentUser)
            findNavController().navigate(action)
        }

        viewModel.cardsList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

}