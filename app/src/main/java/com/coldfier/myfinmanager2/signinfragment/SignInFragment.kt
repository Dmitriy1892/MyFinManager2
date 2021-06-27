package com.coldfier.myfinmanager2.signinfragment

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.coldfier.myfinmanager2.R
import com.coldfier.myfinmanager2.databinding.SignInFragmentBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {

    private lateinit var viewModel: SignInViewModel

    private lateinit var binding: SignInFragmentBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var googleActivityResultLauncher: ActivityResultLauncher<Intent>

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        val googleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.def_web_client_id))
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), googleSignInOptions)

        googleActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                val account = task.getResult(ApiException::class.java)
                SignInFirebaseAuth.firebaseAuthWithGoogle(account!!.idToken!!) {
                    if (it != null) {
                        val action = SignInFragmentDirections.actionSignInFragmentToTransactionsFragment(auth.currentUser!!)
                        findNavController().navigate(action)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Fail to sign-in, please repeat",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SignInFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        binding.googleButon.setSize(SignInButton.SIZE_STANDARD)
        binding.googleButon.setOnClickListener {
            googleActivityResultLauncher.launch(googleSignInClient.signInIntent)
        }

        binding.registerButton.setOnClickListener {
            if (binding.emailEditText.text != null && binding.passwordEditText.text != null) {
                lifecycleScope.launch {

                    if (binding.emailEditText.text.toString() != "" && binding.passwordEditText.toString() != "") {
                        SignInFirebaseAuth.createUserWithEmailAndPassword(
                            binding.emailEditText.text.toString(),
                            binding.passwordEditText.text.toString()
                        ) { user ->
                            if (user != null) {
                                val action = SignInFragmentDirections.actionSignInFragmentToTransactionsFragment(user)
                                findNavController().navigate(action)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Fail to register new user, please repeat",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }

        binding.signInButton.setOnClickListener {
            if (binding.emailEditText.text.toString() != "" && binding.passwordEditText.toString() != "") {
                lifecycleScope.launch {
                    if (binding.emailEditText.text != null && binding.passwordEditText.text != null) {
                        SignInFirebaseAuth.signInWithEmailAndPassword(
                            binding.emailEditText.text.toString(),
                            binding.passwordEditText.text.toString()
                        ) { user ->
                            if (user != null) {
                                val action = SignInFragmentDirections.actionSignInFragmentToTransactionsFragment(user)
                                findNavController().navigate(action)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Fail to sign-in, please repeat",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
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