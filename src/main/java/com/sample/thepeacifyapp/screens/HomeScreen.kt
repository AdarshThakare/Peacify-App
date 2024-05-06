package com.sample.thepeacifyapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sample.thepeacifyapp.R
import com.sample.thepeacifyapp.components.ButtonComponent
import com.sample.thepeacifyapp.components.NormalTextComponent
import com.sample.thepeacifyapp.data.SignUpViewModel
import com.sample.thepeacifyapp.navigation.PeacifyRouter
import com.sample.thepeacifyapp.navigation.Screen

@Composable
fun HomeScreen(loginViewModel : SignUpViewModel = viewModel()){
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = stringResource(R.string.home), size = 48.sp)
            ButtonComponent(value = stringResource(R.string.log_out), onButtonClicked = {
                loginViewModel.logout()
                PeacifyRouter.navigateTo(Screen.SignUpScreen)
            },
                isEnabled = true)
        }
     }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}