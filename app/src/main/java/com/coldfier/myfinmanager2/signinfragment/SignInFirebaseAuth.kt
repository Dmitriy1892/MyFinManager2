package com.coldfier.myfinmanager2.signinfragment

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object SignInFirebaseAuth {

    fun createUserWithEmailAndPassword(email: String, password: String, signed: (currentUser: FirebaseUser?) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { result ->
            if (result.isSuccessful) {
                val user = Firebase.auth.currentUser
                signed(user!!)
            } else {
                signed(null)
            }
        }.addOnFailureListener {
            signed(null)
        }
    }

    fun signInWithEmailAndPassword(email: String, password: String, signed: (currentUser: FirebaseUser?) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    val user = Firebase.auth.currentUser
                    signed(user!!)
                } else {
                    signed(null)
                }
            }
            .addOnFailureListener {
                signed(null)
            }
    }

    fun firebaseAuthWithGoogle(idToken: String, signed: (currentUser: FirebaseUser?) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        Firebase.auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = Firebase.auth.currentUser
                    signed(user)
                }  else {
                    signed(null)
                }
            }.addOnFailureListener {
                signed(null)
            }
    }
}