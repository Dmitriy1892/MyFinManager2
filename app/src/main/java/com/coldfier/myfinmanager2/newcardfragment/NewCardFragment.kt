package com.coldfier.myfinmanager2.newcardfragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.coldfier.myfinmanager2.databinding.NewCardFragmentBinding

class NewCardFragment : Fragment() {

    private lateinit var viewModel: NewCardViewModel
    private lateinit var binding: NewCardFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = NewCardFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, NewCardViewModelFactory(requireActivity().application)).get(NewCardViewModel::class.java)

        binding.newCardSaveButton.setOnClickListener {
            binding.apply {
                if (
                    newCardNameEditText.text.isNotEmpty() &&
                    newCardNumberEditText.text.isNotEmpty() &&
                    newCardBalanceEditText.text.isNotEmpty()
                ) {
                    viewModel.createNewCard(
                        newCardNameEditText.text.toString(),
                        newCardNumberEditText.text.toString().toInt(),
                        newCardBalanceEditText.text.toString().toDouble(),
                        newCardCurrencySpinner.selectedItem.toString()
                    )

                    findNavController().navigateUp()
                }
            }
        }

        return binding.root
    }
}