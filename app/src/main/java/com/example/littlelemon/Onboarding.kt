package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import android.content.SharedPreferences
import android.content.Context
import android.provider.Settings.Global.putString
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit

@Composable
fun OnBoarding(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
        )
        Text(
            text = "Let's Get to Know You",
            modifier = Modifier
                .background(Color(0xFF495E57))
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentSize(Alignment.Center),
            style = TextStyle(color = Color.White),
            fontSize = 25.sp
        )
        Text(
            text = "Personal Information",
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(top = 40.dp, start = 40.dp),
            style = TextStyle(color = Color.Black),
            fontSize = 20.sp
        )
        var firstName by remember { mutableStateOf("") }

        TextField(
            value = firstName,
            onValueChange = { newText -> firstName = newText },
            label = { Text("First Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .background(Color.White, RoundedCornerShape(322.dp))
                .height(50.dp),
            textStyle = TextStyle(fontSize = 12.sp),
        )

        var lastName by remember { mutableStateOf("") }

        TextField(
            value = lastName,
            onValueChange = { newText -> lastName = newText },
            label = { Text("Last Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .background(Color.White, RoundedCornerShape(322.dp))
                .height(50.dp),
            textStyle = TextStyle(fontSize = 12.sp),
        )

        var email by remember { mutableStateOf("") }

        TextField(
            value = email,
            onValueChange = { newText -> email = newText },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .background(Color.White, RoundedCornerShape(322.dp))
                .height(50.dp),
            textStyle = TextStyle(fontSize = 12.sp),
        )

        var showDialog by remember { mutableStateOf(false) }
        Button(
            onClick = {
                if(!email.isBlank() && !lastName.isBlank() && !firstName.isBlank()) {

                    sharedPreferences.edit(commit = true) {
                        putString("first", firstName)
                    }
                    sharedPreferences.edit(commit = true) {
                        putString("last", lastName)
                    }
                    sharedPreferences.edit(commit = true) {
                        putString("email", email)
                    }

                    navController.navigate(Home.route)
                } else {
                    showDialog = true
                }
                      },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        ) {
            Text(
                text = "Register",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }


        if(showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Failed Registration") },
                text = { Text("Registration unsuccessful. Please enter all data.") },
                confirmButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text("Dismiss")
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun OnBoardingPreview() {
    //OnBoarding(navC)
}
