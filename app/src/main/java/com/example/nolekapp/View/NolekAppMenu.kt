package com.example.nolekapp.View

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.nolekapp.ApiActivity
import com.example.nolekapp.CameraActivity
import com.example.nolekapp.DatabaseActivity
import com.example.nolekapp.MainActivity

@Composable
fun NolekAppMenu() {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.align(Alignment.TopCenter)
        )
        {
            Text("Nolek App", style = MaterialTheme.typography.headlineMedium)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


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
                val intent = Intent(context, ApiActivity::class.java)
                context.startActivity(intent)
            }) {
                Text("API")
            }
        }

    }
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Button(onClick = {
            val intent = Intent(context, CameraActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Camera")
        }
    }
}
