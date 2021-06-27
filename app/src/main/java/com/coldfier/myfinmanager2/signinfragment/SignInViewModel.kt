package com.coldfier.myfinmanager2.signinfragment

import androidx.activity.result.ActivityResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

class SignInViewModel: ViewModel() {

    private val _authUserState = MutableLiveData(false)
    val authUserState: LiveData<Boolean>
        get() = _authUserState

    private val _makeToastState = MutableLiveData(false)
    val makeToastState: LiveData<Boolean>
        get() = _makeToastState

    fun googleSignInMoveToApp(result: ActivityResult) {
        viewModelScope.launch {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.getResult(ApiException::class.java)
            SignInFirebaseAuth.firebaseAuthWithGoogle(account!!.idToken!!) {
                if (it != null) {
                    _authUserState.value = true
                } else {
                    _makeToastState.value = true
                }
            }
        }
    }

    fun registerButtonClicked(email: String, password: String) {
        viewModelScope.launch {
            SignInFirebaseAuth.createUserWithEmailAndPassword(email, password) { user ->
                if (user != null) {
                    _authUserState.value = true
                } else {
                    _makeToastState.value = true
                }
            }
        }
    }

    fun emailSignInButtonClicked(email: String, password: String) {
        viewModelScope.launch {
            SignInFirebaseAuth.signInWithEmailAndPassword(email, password) { user ->
                if (user != null) {
                    _authUserState.value = true
                } else {
                    _makeToastState.value = true
                }
            }
        }
    }
}