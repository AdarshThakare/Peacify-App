package com.sample.thepeacifyapp.data

data class RegisterationUIState (
    var firstName : String = "",
    var lastName : String = "",
    var email : String = "",
    var password : String = "",

    var firstNameError : Boolean = false,
    var lastNameError : Boolean = false,
    var emailError : Boolean = false,
    var passwordError : Boolean = false,

    )
