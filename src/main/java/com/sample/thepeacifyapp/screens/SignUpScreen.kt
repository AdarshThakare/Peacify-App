package com.sample.thepeacifyapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sample.thepeacifyapp.R
import com.sample.thepeacifyapp.components.ButtonComponent
import com.sample.thepeacifyapp.components.ClickableLoginComponent
import com.sample.thepeacifyapp.components.DividerText
import com.sample.thepeacifyapp.components.InvertTextComponent
import com.sample.thepeacifyapp.components.NormalTextComponent
import com.sample.thepeacifyapp.components.PasswordTextFieldComponent
import com.sample.thepeacifyapp.components.TextFieldComponent
import com.sample.thepeacifyapp.data.SignUpViewModel
import com.sample.thepeacifyapp.data.SignUpUIEvent
import com.sample.thepeacifyapp.navigation.PeacifyRouter
import com.sample.thepeacifyapp.navigation.Screen

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = viewModel()) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center ){
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Spacer(Modifier.height(20.dp))
                    NormalTextComponent(value = stringResource(id = R.string.app_name),size = 26.sp)
                    InvertTextComponent(value = stringResource(id = R.string.account_create))
                    Spacer(Modifier.height(20.dp))
                    TextFieldComponent(labelValue = stringResource(id = R.string.first_name),
                        painterResource(id =R.drawable.icons8_user_profile_64),
                        KeyboardOptions(imeAction = ImeAction.Next),
                        onTextSelected = {
                            signUpViewModel.onEvent(SignUpUIEvent.FirstNameChanged(it))
                        },
                        errorStatus = signUpViewModel.registerationUIState.value.firstNameError)
                    Spacer(Modifier.height(10.dp))
                    TextFieldComponent(labelValue = stringResource(id = R.string.last_name),
                        painterResource(id =R.drawable.icons8_name_64),
                        KeyboardOptions(imeAction = ImeAction.Next),
                        onTextSelected = {
                            signUpViewModel.onEvent(SignUpUIEvent.LastNameChanged(it))
                        },
                        errorStatus = signUpViewModel.registerationUIState.value.lastNameError)
                    Spacer(Modifier.height(10.dp))
                    TextFieldComponent(labelValue = stringResource(id = R.string.emailid),
                        painterResource(id =R.drawable.email_64),
                        KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                        onTextSelected = {
                            signUpViewModel.onEvent(SignUpUIEvent.EmailChanged(it))
                        },
                        errorStatus = signUpViewModel.registerationUIState.value.emailError)
                    Spacer(Modifier.height(10.dp))
                    PasswordTextFieldComponent(labelValue = stringResource(id = R.string.passy),
                        painterResource(id =R.drawable.password_64),
                        KeyboardOptions(keyboardType = KeyboardType.Password,imeAction = ImeAction.Done),
                        onTextSelected = {
                            signUpViewModel.onEvent(SignUpUIEvent.PasswordChanged(it))
                        },
                        errorStatus = signUpViewModel.registerationUIState.value.passwordError
                    )
                    Spacer(Modifier.height(100.dp))
                    ButtonComponent(value = stringResource(id = R.string.register) ,
                        onButtonClicked = {
                            signUpViewModel.onEvent(SignUpUIEvent.RegisterButtonClicked)
                            PeacifyRouter.navigateTo(Screen.HomeScreen)
                        }, isEnabled = signUpViewModel.allValidationPassed.value)
                    Spacer(Modifier.height(50.dp))
                    DividerText()
                    Spacer(Modifier.height(10.dp))
                    ClickableLoginComponent(onTextSelected = { PeacifyRouter.navigateTo(Screen.LoginScreen)},
                        initialText = stringResource(id = R.string.haveaccount),
                        clickableText = stringResource(id = R.string.Login))
                }
            }

        if(signUpViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }


        }
    }

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfSignUpScreen(){
    SignUpScreen()
}

