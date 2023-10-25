package com.example.nolekapp

import com.example.nolekapp.ViewModel.ImagePickerViewModel
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nolekapp.View.InputManager
import com.example.nolekapp.View.SwitchManager
import com.example.nolekapp.ViewModel.DynamicSwitchViewModel
import com.example.nolekapp.ViewModel.StatusViewModel


@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {

    private val switchViewModel: DynamicSwitchViewModel by viewModels()
    private val viewModel: ImagePickerViewModel by viewModels()
    private val statusViewModel: StatusViewModel by viewModels()


        private lateinit var switchManager: SwitchManager
    private lateinit var inputManager: InputManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val switchesLayout = findViewById<LinearLayout>(R.id.switches_layout)


        switchManager = SwitchManager(applicationContext, parentLayout = switchesLayout)
        inputManager = InputManager(this, switchManager)

        val createSwitchesButton = findViewById<Button>(R.id.create_switches_button)
        createSwitchesButton.setOnClickListener {
            statusViewModel.showNextQuestion()
        }

        val removeSwitchesButton = findViewById<Button>(R.id.remove_switches_button)
        removeSwitchesButton.setOnClickListener {
            switchManager.removeAllSwitches()
        }

        // Knappen til at importere et billede
        val importerBilledeKnap = findViewById<Button>(R.id.importer_billede_knap)
        importerBilledeKnap.setOnClickListener {
            viewModel.handleImportButtonClick(this)
        }

        val allOkButton = findViewById<Button>(R.id.button)
        allOkButton.setOnClickListener {
            switchViewModel.setAllSwitchesToTrue()
        }

        val allNotOkButton = findViewById<Button>(R.id.button3)
        allNotOkButton.setOnClickListener {
            switchViewModel.setAllSwitchesToFalse()
        }

        val backbutton = findViewById<Button>(R.id.backButton)
        backbutton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)

        }
        statusViewModel.completionButton1Visibility.observe(this, { visibility ->
            findViewById<Button>(R.id.completionButton1).visibility = visibility
        })

        statusViewModel.completionButton2Visibility.observe(this, { visibility ->
            findViewById<Button>(R.id.completionButton2).visibility = visibility
        })


    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.handleActivityResult(requestCode, resultCode, data, findViewById(R.id.imageView))
    }




}

