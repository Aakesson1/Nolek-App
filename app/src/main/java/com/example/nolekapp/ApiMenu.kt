package com.example.nolekapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.nolekapp.View.ApiMenu
import com.example.nolekapp.View.NolekAppMenu
import com.example.nolekapp.ui.theme.NolekAppTheme

class ApiMenu : AppCompatActivity() {
    private fun navigateToMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NolekAppTheme {
               ApiMenu(navigateToMenu = ::navigateToMenu)
            }
        }
    }
}
