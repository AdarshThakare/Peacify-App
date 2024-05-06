package com.sample.thepeacifyapp.data.rules

object Validator {
    fun ValidateFirstName(fName : String) : ValidationResult{
        return ValidationResult(
            (!fName.isNullOrEmpty() && fName.length<=20)
        )
    }

    fun ValidateLastName(lName : String) : ValidationResult{
        return ValidationResult(
            (!lName.isNullOrEmpty() && lName.length<=20)
        )
    }

    fun ValidatePassword(password : String) : ValidationResult{
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length>=6)
        )
    }

    fun ValidateEmail(email : String) : ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty() && email.length<=50)
        )
    }
}

data class ValidationResult(
    val status : Boolean = false
)