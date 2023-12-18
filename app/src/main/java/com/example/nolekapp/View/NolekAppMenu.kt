package com.example.nolekapp.View

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.nolekapp.ApiMenu
import com.example.nolekapp.CameraActivity
import com.example.nolekapp.CameraActivityCompose
import com.example.nolekapp.DatabaseActivity
import com.example.nolekapp.MainActivity
import com.example.nolekapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NolekAppMenu() {
    val context = LocalContext.current
    val customIcon: Painter = painterResource(id = R.drawable.baseline_photo_camera_24)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val intent = Intent(context, CameraActivityCompose::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    painter = customIcon,
                    contentDescription = "Custom Camera Icon"
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text("Nolek App", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Text("START")
                }

                Button(onClick = {
                    val intent = Intent(context, DatabaseActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Text("DATABASE")
                }

                Button(onClick = {
                    val intent = Intent(context, ApiMenu::class.java)
                    context.startActivity(intent)
                }) {
                    Text("API")
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
