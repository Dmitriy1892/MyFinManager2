package com.coldfier.myfinmanager2.signinfragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.coldfier.myfinmanager2.R
import com.coldfier.myfinmanager2.databinding.SignInFragmentBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFragment : Fragment() {

    private lateinit var viewModel: SignInViewModel

    private lateinit var binding: SignInFragmentBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var googleActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SignInFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        googleActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                viewModel.googleSignInMoveToApp(it)
            }
        }

        viewModel.makeToastState.observe(viewLifecycleOwner) {
            if (it) Toast.makeText(requireContext(), "Login failed, please repeat", Toast.LENGTH_SHORT).show()
        }

        viewModel.authUserState.observe(viewLifecycleOwner) {
            if (it && auth.currentUser != null) {
                val action = SignInFragmentDirections.actionSignInFragmentToTransactionsFragment(auth.currentUser!!)
                findNavController().navigate(action)
            }
        }

        binding.googleButon.setSize(SignInButton.SIZE_STANDARD)
        binding.googleButon.setOnClickListener {
            val googleSignInOptions =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.def_web_client_id))
                    .requestEmail()
                    .build()

            val googleSignInClient = GoogleSignIn.getClient(requireContext(), googleSignInOptions)
            googleActivityResultLauncher.launch(googleSignInClient.signInIntent)
        }

        binding.registerButton.setOnClickListener {
            if (binding.emailEditText.text.toString() != "" && binding.passwordEditText.text.toString() != "") {
                viewModel.registerButtonClicked(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
            }
        }

        binding.signInButton.setOnClickListener {
            if (binding.emailEditText.text.toString() != "" && binding.passwordEditText.text.toString() != "") {
                viewModel.emailSignInButtonClicked(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val action = SignInFragmentDirections.actionSignInFragmentToTransactionsFragment(currentUser)
            findNavController().navigate(action)
        }
    }
}