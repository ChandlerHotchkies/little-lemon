package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavController

@Composable
fun Profile(navController: NavController) {

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    var firstName = sharedPreferences.getString("first", "")
    var lastName = sharedPreferences.getString("last", "")
    var email = sharedPreferences.getString("email", "")

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
        )

        Text(
            text = "Personal Information",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 40.dp, start = 40.dp),
            style = TextStyle(color = Color.Black),
            fontSize = 35.sp
        )
        //var firstName by remember { mutableStateOf("") }

        TextField(
            value = firstName ?: "",
            onValueChange = { newText -> firstName = newText },
            label = { Text("First Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .background(Color.White, RoundedCornerShape(322.dp))
                .height(50.dp),
            textStyle = TextStyle(fontSize = 12.sp),
        )

        //var lastName by remember { mutableStateOf("") }

        TextField(
            value = lastName ?: "",
            onValueChange = { newText -> lastName = newText },
            label = { Text("Last Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .background(Color.White, RoundedCornerShape(322.dp))
                .height(50.dp),
            textStyle = TextStyle(fontSize = 12.sp),
        )

        //var email by remember { mutableStateOf("") }

        TextField(
            value = email ?: "",
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
                sharedPreferences.edit(commit = true) {
                    putString("first", "")
                }
                sharedPreferences.edit(commit = true) {
                    putString("last", "")
                }
                sharedPreferences.edit(commit = true) {
                    putString("email", "")
                }
                navController.navigate(OnBoarding.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 16.dp)
                .background(Color.Yellow)
        ) {
            Text(
                text = "Sign Out",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
}