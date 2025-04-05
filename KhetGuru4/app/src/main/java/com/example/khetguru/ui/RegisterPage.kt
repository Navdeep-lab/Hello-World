@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.khetguru.ui

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController

@Composable
fun SignScreen(navController: NavController,authViewModel: AuthViewModel) {
    var emailadd by remember { mutableStateOf("") }
    var passwd by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val cpasswordVisible = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val authState=authViewModel.authState.observeAsState()
    val scrollState= rememberScrollState()

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
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(0.dp))
            Image(
                painter = painterResource(id= com.example.khetguru.R.drawable.farm2),
                contentDescription = "Asset",
                modifier = Modifier.padding(bottom = 0.dp).size(350.dp)
            )

            Text(
                "Create An Account",
                style = TextStyle(
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF388E3C)

                )
            )
            Spacer(modifier = Modifier.height(10.dp))
//            OutlinedTextField(
//                value = name,
//                onValueChange = { name = it },
//                // label = { Text("NAME") },
//                placeholder = {
//                    Text(
//                        "Name", style = TextStyle(
//                            color = Color(0xFF90A4AE)
//                        )
//                    )
//
//                },
//                shape = RoundedCornerShape(
//                    topStart = 10.dp,
//                    bottomEnd = 10.dp
//                ),
//                singleLine = true,
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = Color(0xFF4A4AFF),
//                    unfocusedBorderColor = Color(0xFF4A4AFF),
//                    focusedTextColor = Color.Black,
//                    unfocusedTextColor = Color.Black
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//            )
//            //Spacer(modifier = Modifier.height(10.dp))
//            OutlinedTextField(
//                value = phone,
//                onValueChange = { phone = it },
//                // label = { Text("NAME") },
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Phone
//                ),
//                placeholder = {
//                    Text(
//                        "Phone", style = TextStyle(
//                            color = Color(0xFF90A4AE)
//                        )
//                    )
//                },
//                shape = RoundedCornerShape(
//                    topStart = 10.dp,
//                    bottomEnd = 10.dp
//                ),
//                singleLine = true,
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = Color(0xFF4A4AFF),
//                    unfocusedBorderColor = Color(0xFF4A4AFF),
//                    focusedTextColor = Color.Black,
//                    unfocusedTextColor = Color.Black
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//            )
            //Spacer(modifier = Modifier.height(10.dp))
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
                    focusedBorderColor =   Color(0xFF388E3C),
                    unfocusedBorderColor =  Color(0xFF388E3C),
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
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                // label = { Text("NAME") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (cpasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { cpasswordVisible.value = !cpasswordVisible.value }) {
                        Icon(
                            painter = painterResource(id = if (cpasswordVisible.value) com.example.khetguru.R.drawable.visibility else com.example.khetguru.R.drawable.baseline_visibility_off_24),
                            contentDescription = if (cpasswordVisible.value) "Hide Password" else "Show Password",
                            tint = Color(0xFF388E3C)
                        )
                    }
                },
                placeholder = {
                    Text(
                        "Confirm Password", style = TextStyle(
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
            IconButton(onClick={
                authViewModel.signup(emailadd,passwd, confirmPassword)
            },
                modifier = Modifier.fillMaxWidth(),
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xFF388E3C)))
            {
                Text("Sign Up", style = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight.SemiBold,
                    color =  Color.White
                ))
            }
            IconButton(onClick = {navController.navigate("login")}, modifier = Modifier.fillMaxWidth()) {
                Text("Already have an account ?", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color =  Color(0xFF388E3C)
                ))
                Spacer(modifier = Modifier.height(30.dp))
            }
            Spacer(modifier = Modifier.height(30.dp))

        }
    }
}
