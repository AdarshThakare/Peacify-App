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
import com.sample.thepeacifyapp.components.UnderlinedTextComposable
import com.sample.thepeacifyapp.data.LoginUIEvent
import com.sample.thepeacifyapp.data.LoginViewModel
import com.sample.thepeacifyapp.data.SignUpViewModel
import com.sample.thepeacifyapp.data.SignUpUIEvent
import com.sample.thepeacifyapp.navigation.PeacifyRouter
import com.sample.thepeacifyapp.navigation.Screen
import com.sample.thepeacifyapp.navigation.SystemBackButtonHandler

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center ) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(Modifier.height(35.dp))
                NormalTextComponent(value = stringResource(id = R.string.hitext), size = 22.sp)
                InvertTextComponent(value = stringResource(id = R.string.welcome_text))
                Spacer(Modifier.height(35.dp))
                TextFieldComponent(
                    labelValue = stringResource(id = R.string.emailid),
                    painterResource(id = R.drawable.email_64),
                    KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.LoginUIState.value.emailError
                )
                Spacer(Modifier.height(10.dp))
                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.passy),
                    painterResource(id = R.drawable.password_64),
                    KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.LoginUIState.value.passwordError
                )
                Spacer(Modifier.height(25.dp))
                UnderlinedTextComposable(value = stringResource(id = R.string.forgot), size = 20.sp)
                Spacer(Modifier.height(150.dp))
                ButtonComponent(value = stringResource(id = R.string.Login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                        PeacifyRouter.navigateTo(Screen.HomeScreen)
                    }, isEnabled = loginViewModel.allValidationPassed.value
                )
                Spacer(Modifier.height(30.dp))
                DividerText()
                Spacer(Modifier.height(25.dp))
                ClickableLoginComponent(
                    onTextSelected = { PeacifyRouter.navigateTo(Screen.SignUpScreen) },
                    initialText = stringResource(id = R.string.havenot_account),
                    clickableText = stringResource(id = R.string.register)
                )
            }
            if(loginViewModel.LoginInProgress.value) {
                CircularProgressIndicator()
            }


        }
    }

    SystemBackButtonHandler {
        PeacifyRouter.navigateTo(Screen.SignUpScreen)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}