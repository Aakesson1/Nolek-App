package com.example.nolekapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.compose.setContent
import com.example.nolekapp.Database.MongoDB.app
import com.example.nolekapp.View.NolekAppMenu
import com.example.nolekapp.ui.theme.NolekAppTheme
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.kotlin.internal.platform.runBlocking
import io.realm.kotlin.mongodb.Credentials

class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                NolekAppTheme {
                    NolekAppMenu()
                }
            }
        }
    }

