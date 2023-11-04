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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NolekAppTheme {
                CustomLayout(statusViewModel = statusViewModel)
            }
        }
    }
/*
    private fun setupUIComponents() {
        val okButton = findViewById<Button>(R.id.ok)
        val notOkButton = findViewById<Button>(R.id.notOk)
        val questionTextView = findViewById<TextView>(R.id.questionTextView)
        okButton.isEnabled = false
        notOkButton.isEnabled = false

        statusViewModel.currentQuestion.observe(this) { question ->
            questionTextView.text = question
            val isQuestionPresent = !question.isNullOrBlank()
            okButton.isEnabled = isQuestionPresent
            notOkButton.isEnabled = isQuestionPresent
        }

        findViewById<Button>(R.id.ok).setOnClickListener {
            statusViewModel.showNextQuestion()
        }

        findViewById<Button>(R.id.notOk).setOnClickListener {
            val input = EditText(this)
            val builder = AlertDialog.Builder(this)
                .setTitle("Angiv begrundelse")
                .setView(input)
                .setPositiveButton("OK") { _, _ ->
                    statusViewModel.showNextQuestion()
                }
                .setNegativeButton("Annuller") { dialog, _ ->
                    dialog.dismiss()
                }
            builder.show()
        }

        findViewById<Button>(R.id.generateButton).setOnClickListener {

            val input = EditText(this)
            val builder = AlertDialog.Builder(this)
                .setTitle("Angiv navn")
                .setView(input)
                .setPositiveButton("OK") { _, _ ->
                    val name = input.text.toString()
                    if(name.isNotEmpty()) {
                        val setNameEvent = AppEvent.SetName(name)
                        testResultatViewModel.onEvent(setNameEvent)
                        }
                }
                .setNegativeButton("Annuller") { dialog, _ ->
                    dialog.dismiss()
                }
            builder.show()

            statusViewModel.setQuestionsBasedOnPoints(
                findViewById<EditText>(R.id.testInput).text.toString().toIntOrNull() ?: 0
            )
        }

        findViewById<Button>(R.id.backButton).setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }

 */

}