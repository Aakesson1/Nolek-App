package com.example.nolekapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.realm.kotlin.RealmConfiguration

import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nolekapp.Database.Data.TestResultat
import com.example.nolekapp.Database.TestResultatRepository
import com.example.nolekapp.Database.TestResultatRespositoryimpl
import com.example.nolekapp.View.TestResultatScreen
import com.example.nolekapp.ui.theme.NolekAppTheme
import com.example.nolekapp.ViewModel.TestResultatViewModel
import io.realm.kotlin.Realm

// ...

class DatabaseActivity : AppCompatActivity() {

    private lateinit var realm: Realm  // Use lateinit to initialize the Realm later

    private val viewModel by viewModels<TestResultatViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(TestResultatViewModel::class.java)) {
                    // Ensure the Realm instance is initialized before using it
                    if (!::realm.isInitialized) {
                        val configuration = RealmConfiguration.Builder(schema = setOf(TestResultat::class))
                            .name("default.realm")
                            .build()
                        realm = Realm.open(configuration)
                    }
                    val repository = TestResultatRespositoryimpl(realm)
                    @Suppress("UNCHECKED_CAST")
                    return TestResultatViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
    private fun navigateToMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val configuration = RealmConfiguration.Builder(schema = setOf(TestResultat::class))
            .name("default.realm")
            .build()
        realm = Realm.open(configuration)

        setContent {
            NolekAppTheme {
                // Use the viewModel from above, not hiltViewModel here
                val state by viewModel.state.collectAsState()
                TestResultatScreen(state = state, onEvent = viewModel::onEvent, navigateToMenu = ::navigateToMenu)
            }
        }
    }


}
