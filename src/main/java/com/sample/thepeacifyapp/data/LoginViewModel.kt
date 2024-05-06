package com.sample.thepeacifyapp.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.sample.thepeacifyapp.data.rules.Validator
import com.sample.thepeacifyapp.navigation.PeacifyRouter
import com.sample.thepeacifyapp.navigation.Screen

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName
    var LoginUIState = mutableStateOf(LoginUIState())
    var allValidationPassed = mutableStateOf(false)
    var LoginInProgress = mutableStateOf(false)

    fun onEvent(event : LoginUIEvent){
        validateDataWithRules()
        when(event){
            is LoginUIEvent.EmailChanged -> {
                LoginUIState.value = LoginUIState.value.copy(
                    email = event.email
                )
            }
            is LoginUIEvent.PasswordChanged -> {
                LoginUIState.value = LoginUIState.value.copy(
                    password = event.password
                )
            }
            is LoginUIEvent.LoginButtonClicked -> {
                Login()
            }
        }
    }

    private fun validateDataWithRules() {
        val emailResult = Validator.ValidateEmail(
            email = LoginUIState.value.email
        )

        val passwordResult = Validator.ValidatePassword(
            password = LoginUIState.value.password
        )

        LoginUIState.value = LoginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )

        if(emailResult.status && passwordResult.status){
            allValidationPassed.value=true
        }else{
            allValidationPassed.value=false
        }
    }

    private fun Login() {
        LoginInProgress.value=true
        val email = LoginUIState.value.email
        val password = LoginUIState.value.password

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                Log.d(TAG,"Inside Login, success")
                Log.d(TAG,"${it.isSuccessful}")

                LoginInProgress.value=false
                if(it.isSuccessful){
                    PeacifyRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener{
                Log.d(TAG,"Inside Login, failure")
                Log.d(TAG,"${it.localizedMessage}")

            }
    }
}