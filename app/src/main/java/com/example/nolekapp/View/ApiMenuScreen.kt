package com.example.nolekapp.View

import com.example.nolekapp.PostApiActivity


import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.nolekapp.ApiActivity
import com.example.nolekapp.CameraActivity
import com.example.nolekapp.DatabaseActivity
import com.example.nolekapp.MainActivity
import com.example.nolekapp.MenuActivity
import com.example.nolekapp.Model.AppEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiMenu(navigateToMenu: () -> Unit) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Api Menu")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigateToMenu()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    )
    {innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        )  {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.align(Alignment.TopCenter)
            ) {

            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(onClick = {
                    val intent = Intent(context, ApiActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Text("Get")
                }

                Button(onClick = {
                    val intent = Intent(context, PostApiActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Text("Post")
                }
            }
        }
    }
}


