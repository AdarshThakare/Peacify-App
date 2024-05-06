package com.sample.thepeacifyapp.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.thepeacifyapp.R
import com.sample.thepeacifyapp.ui.theme.Pink40
import com.sample.thepeacifyapp.ui.theme.Pink80
import com.sample.thepeacifyapp.ui.theme.Purple40
import com.sample.thepeacifyapp.ui.theme.Purple80
import com.sample.thepeacifyapp.ui.theme.PurpleGrey40
import com.sample.thepeacifyapp.ui.theme.PurpleGrey80


@Composable
fun NormalTextComponent(value : String, size : TextUnit ){
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        color = Color.Black,
        style = TextStyle(
            fontSize = size,
            fontWeight = FontWeight(400),
            fontStyle = FontStyle.Normal
            ),
        textAlign = TextAlign.Center
    )

}

@Composable
fun InvertTextComponent(value : String){
    Text(text = value,
        Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 4.dp)
            .heightIn(min = 40.dp),

        color = Color.Black,
        style = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
        ),
        textAlign = TextAlign.Center
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(labelValue : String, painterResource : Painter,
                       keyboardOptions : KeyboardOptions, onTextSelected: (String) -> Unit,
                       errorStatus : Boolean = false){
    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = labelValue)
                },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
            cursorColor = Purple80
        ),
        keyboardOptions = keyboardOptions,
        value = textValue ,
        onValueChange =  {
            textValue = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource,
                contentDescription = "User Profile")},
        singleLine = true,
        maxLines = 1,
        isError =!errorStatus

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextFieldComponent(labelValue : String, painterResource : Painter,
                               keyboardOptions : KeyboardOptions, onTextSelected: (String) -> Unit,
                               errorStatus : Boolean = false) {
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = labelValue)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
            cursorColor = Purple80
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        },
        value = password ,
        onValueChange =  {
            password = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource,
                contentDescription = "User Profile")},
        singleLine = true,
        maxLines = 1,
        isError =!errorStatus,
        trailingIcon ={
            var iconImage = if(passwordVisibility){
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            var description = if(passwordVisibility){
                "Hide Password"
            } else {
                "Show Password"
            }

            IconButton( onClick = {passwordVisibility = !passwordVisibility})
            {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if(passwordVisibility)
        {
            VisualTransformation.None
        }
        else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun ButtonComponent(value : String , onButtonClicked : () -> Unit , isEnabled : Boolean = false){
    Button(onClick = { onButtonClicked.invoke() }, modifier = Modifier
        .fillMaxWidth()
        .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = isEnabled
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(50.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Pink80, Purple40)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                color = Color.White)
        }
    }
}

@Composable
fun DividerText(){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.DarkGray,
            thickness = 3.dp)
        Text(text = " O R ",
            modifier = Modifier.padding(start = 5.dp, end = 5.dp),
            fontSize = 18.sp,
            color = Purple40)
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.DarkGray,
            thickness = 3.dp)
    }
}

@Composable
fun ClickableLoginComponent(onTextSelected : (String) -> Unit, initialText : String, clickableText : String) {
    val initialText = initialText
    val loginText = clickableText

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Color.Blue)){
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
    ClickableText(
        modifier = Modifier
        .fillMaxWidth(),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight(300),
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = {offset ->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also{
                span ->
                Log.d("Clickable Text","{${span.item}")

                if(span.item==loginText){
                    onTextSelected(span.item)
                }
            }

    })
}

@Composable
fun UnderlinedTextComposable(value : String, size : TextUnit){
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        color = PurpleGrey40,
        style = TextStyle(
            fontSize = size,
            fontWeight = FontWeight(400),
            fontStyle = FontStyle.Normal
        ),
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}