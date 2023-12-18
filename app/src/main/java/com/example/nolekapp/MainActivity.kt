package com.example.nolekapp

import CustomLayout
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nolekapp.Model.AppEvent
import com.example.nolekapp.ViewModel.ImagePickerViewModel
import com.example.nolekapp.ViewModel.StatusViewModel
import com.example.nolekapp.ViewModel.TestResultatViewModel
import com.example.nolekapp.ui.theme.NolekAppTheme

class MainActivity : AppCompatActivity() {

    private val statusViewModel: StatusViewModel by viewModels()
    private val testResultatViewModel: TestResultatViewModel by viewModels()
    private fun navigateToMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NolekAppTheme {
                CustomLayout(statusViewModel = statusViewModel,navigateToMenu = ::navigateToMenu)
            }
        }
    }

}