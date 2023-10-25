package com.example.nolekapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.nolekapp.ViewModel.TestResultatViewModel

import androidx.activity.compose.setContent

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.nolekapp.Database.AppDatabase
import com.example.nolekapp.View.TestResultatScreen
import com.example.nolekapp.ui.theme.NolekAppTheme


// ...

class DatabaseActivity : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "appdatabase.db"
        ).addMigrations(AppDatabase.MIGRATION_1_2).build()
    }
    private val viewModel by viewModels<TestResultatViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TestResultatViewModel(db.dao) as T
                }
            }
        }
    )
    private fun navigateToMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navigateToMenu: () -> Unit = {
            navigateToMenu()
        }

        setContent {
            NolekAppTheme {
                val state by viewModel.state.collectAsState()
                TestResultatScreen(state = state, onEvent = viewModel::onEvent, navigateToMenu = navigateToMenu)
            }
        }
    }
}