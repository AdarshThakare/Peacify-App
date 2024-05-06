package com.sample.thepeacifyapp.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.sample.thepeacifyapp.data.rules.Validator
import com.sample.thepeacifyapp.navigation.PeacifyRouter
import com.sample.thepeacifyapp.navigation.Screen

class SignUpViewModel : ViewModel() {
    var registerationUIState = mutableStateOf(RegisterationUIState())
    var allValidationPassed = mutableStateOf(false)
    var signUpInProgress = mutableStateOf(false)
    fun onEvent(event : SignUpUIEvent){
        validateDataWithRules()
        when(event){
            is SignUpUIEvent.FirstNameChanged -> {
                registerationUIState.value = registerationUIState.value.copy(
                    firstName = event.firstName
                )
            }
            is SignUpUIEvent.LastNameChanged -> {
                registerationUIState.value = registerationUIState.value.copy(
                    lastName = event.lastName
                )
            }
            is SignUpUIEvent.EmailChanged -> {
                registerationUIState.value = registerationUIState.value.copy(
                    email = event.email
                )
            }
            is SignUpUIEvent.PasswordChanged -> {
                registerationUIState.value = registerationUIState.value.copy(
                    password = event.password
                )
            }
            is SignUpUIEvent.RegisterButtonClicked -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        validateDataWithRules()
        createUserInFirebase(
            email = registerationUIState.value.email,
            password = registerationUIState.value.password
        )
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.ValidateFirstName(
            fName = registerationUIState.value.firstName
        )

        val lNameResult = Validator.ValidateLastName(
            lName = registerationUIState.value.lastName
        )

        val emailResult = Validator.ValidateEmail(
            email = registerationUIState.value.email
        )

        val passwordResult = Validator.ValidatePassword(
            password = registerationUIState.value.password
        )

        registerationUIState.value = registerationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )

        if(fNameResult.status && lNameResult.status &&
            emailResult.status && passwordResult.status){
            allValidationPassed.value=true
        }else{
            allValidationPassed.value=false
        }
    }

    private fun createUserInFirebase(email : String , password : String){

        signUpInProgress.value=true
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG,"Inside_addOnCompleteListener")
                Log.d(TAG,"isSuccessful = ${it.isSuccessful}")

                signUpInProgress.value=false
                if(it.isSuccessful){
                    PeacifyRouter.navigateTo((Screen.HomeScreen))
                }
            }
            .addOnFailureListener{
                Log.d(TAG,"Inside_addOnFailureListener")
                Log.d(TAG,"Exception = ${it.message}")
                Log.d(TAG,"Exception = ${it.localizedMessage}")
            }
    }

    fun logout(){
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener = AuthStateListener{
            if(it.currentUser == null){
                Log.d(TAG, "inside sign out Success")
                PeacifyRouter.navigateTo(Screen.LoginScreen)
            }else{
                Log.d(TAG,"inside sign out is not complete")
            }
        }

        firebaseAuth.addAuthStateListener { authStateListener }
    }
}