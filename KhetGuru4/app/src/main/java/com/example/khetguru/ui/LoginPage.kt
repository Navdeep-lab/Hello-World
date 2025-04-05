@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.khetguru.ui

import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import com.example.khetguru.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController,authViewModel: AuthViewModel) {
    var emailadd by remember { mutableStateOf("") }
    var passwd by remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val authState=authViewModel.authState.observeAsState()
    val scrollState=rememberScrollState()
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated->navController.navigate("step1")
            is AuthState.Error->Toast.makeText(context,(authState.value as AuthState.Error).message,Toast.LENGTH_SHORT).show()
            else->Unit
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        LaunchedEffect(text.text) {
            scope.launch {
                scrollState.animateScrollTo(scrollState.maxValue)
            }
        }
        Column(
            modifier = Modifier
                .padding(10.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            LaunchedEffect(text.text) {
                scope.launch {
                    scrollState.animateScrollTo(scrollState.maxValue)
                }
            }
            Spacer(modifier = Modifier.height(60.dp))
            Image(
                painter = painterResource(id = R.drawable.farm1),
                contentDescription = "Asset",
                modifier = Modifier.size(250.dp)
            )
            Text(
                "Login Your Account",
                style = TextStyle(
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF388E3C),

                    )
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = emailadd,
                onValueChange = { emailadd = it },
                // label = { Text("NAME") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                placeholder = {
                    Text(
                        "Email Address", style = TextStyle(
                            color = Color(0xFF90A4AE)
                        )
                    )
                },
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    bottomEnd = 10.dp
                ),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF388E3C),
                    unfocusedBorderColor = Color(0xFF388E3C),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            // Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = passwd,
                onValueChange = { passwd = it },
                // label = { Text("NAME") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),

                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(
                            painter =  painterResource(id = if (passwordVisible.value) com.example.khetguru.R.drawable.visibility else  com.example.khetguru.R.drawable.baseline_visibility_off_24),
                            contentDescription = if (passwordVisible.value) "Hide Password" else "Show Password",
                            tint =  Color(0xFF388E3C)
                        )
                    }
                },
                placeholder = {
                    Text(
                        "Enter Password", style = TextStyle(
                            color = Color(0xFF90A4AE)
                        )
                    )
                },
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    bottomEnd = 10.dp
                ),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF388E3C),
                    unfocusedBorderColor = Color(0xFF388E3C),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            IconButton(onClick = {
                authViewModel.login(emailadd,passwd)
                keyboardController?.hide()

            },
                modifier = Modifier.fillMaxWidth(),
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xFF388E3C))
            ) {
                Text("Login", style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                ))
            }

            IconButton(onClick = {
                navController.navigate("resetpass")
            }, modifier = Modifier.padding(top = 45.dp).fillMaxWidth()) {
                Text("Forget Password ?", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color =  Color(0xFF388E3C)
                ))
            }
            IconButton(onClick = {
                navController.navigate("sign")
            }, modifier = Modifier.padding( bottom = 20.dp).fillMaxWidth()) {
                Text("Don't Have an Account ?", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF388E3C)
                ))
            }
        }
    }
}

