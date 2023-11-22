package com.example.nolekapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.realm.kotlin.RealmConfiguration
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.nolekapp.Database.Constants.APP_ID
import com.example.nolekapp.Database.Data.TestResultat
import com.example.nolekapp.Database.Data.TestResultatRepository
import com.example.nolekapp.Database.MongoDB
import com.example.nolekapp.View.TestResultatScreen
import com.example.nolekapp.ui.theme.NolekAppTheme
import com.example.nolekapp.ViewModel.TestResultatViewModel
import io.realm.kotlin.Realm
import io.realm.kotlin.internal.platform.runBlocking
import io.realm.kotlin.mongodb.App
import okhttp3.Credentials

class DatabaseActivity : AppCompatActivity() {

    private val viewModel by viewModels<TestResultatViewModel> {
        ViewModelFactory()
    }

    private fun navigateToMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NolekAppTheme {
                val state by viewModel.state.collectAsState()
                TestResultatScreen(state = state, onEvent = viewModel::onEvent, navigateToMenu = ::navigateToMenu)
            }
        }
    }

    inner class ViewModelFactory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TestResultatViewModel::class.java)) {
                return TestResultatViewModel(MongoDB) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

