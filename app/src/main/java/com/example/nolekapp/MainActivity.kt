package com.example.nolekapp

import com.example.nolekapp.ViewModel.ImagePickerViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.nolekapp.View.InputManager
import com.example.nolekapp.View.SwitchManager
import com.example.nolekapp.ViewModel.DynamicSwitchViewModel


@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {

    private val switchViewModel: DynamicSwitchViewModel by viewModels()
    private val viewModel: ImagePickerViewModel by viewModels()

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
            inputManager.showSwitchCountDialog()
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


    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.handleActivityResult(requestCode, resultCode, data, findViewById(R.id.imageView))
    }





}

