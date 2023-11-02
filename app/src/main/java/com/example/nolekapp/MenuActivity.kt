package com.example.nolekapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.compose.setContent
import com.example.nolekapp.View.NolekAppMenu
import com.example.nolekapp.ui.theme.NolekAppTheme
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
