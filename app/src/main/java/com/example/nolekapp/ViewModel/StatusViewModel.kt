package com.example.nolekapp.ViewModel

import android.app.Application
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StatusViewModel(application: Application) : AndroidViewModel(application) {
    val questionList = listOf(
        "Question 1",
        "Question 2",
        "Question 3",
        // Add more questions as needed
    )
    private var currentQuestionIndex = 0
    private val app = application

    val completionButton1Visibility = MutableLiveData<Int>()
    val completionButton2Visibility = MutableLiveData<Int>()


    fun showPointCountDialog() {
        viewModelScope.launch {
            val builder = AlertDialog.Builder(app)
            builder.setTitle("Enter point count")

            val input = EditText(app)
            input.inputType = InputType.TYPE_CLASS_NUMBER
            builder.setView(input)

            builder.setPositiveButton("OK") { _, _ ->
                val pointCount = input.text.toString().toIntOrNull()
                if (pointCount != null && pointCount > 0) {
                    // Start the process of displaying questions based on the point count
                } else {
                    // Handle invalid input (e.g., if the user didn't enter a positive number)
                    Toast.makeText(app, "Invalid point count", Toast.LENGTH_SHORT).show()
                }
            }

            builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

            builder.show()
        }
    }

    fun showJustificationDialog() {
        val builder = AlertDialog.Builder(app)
        builder.setTitle("Justification")

        val input = EditText(app)
        builder.setView(input)

        builder.setPositiveButton("Save") { _, _ ->
            val justification = input.text.toString()
            // Handle the user's justification (e.g., save it to the database)
            currentQuestionIndex++
            showNextQuestion()
        }

        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    fun showNextQuestion() {
        if (currentQuestionIndex < questionList.size) {
            val question = questionList[currentQuestionIndex]

            val builder = AlertDialog.Builder(app)
            builder.setTitle("Question ${currentQuestionIndex + 1}")
            builder.setMessage(question)

            builder.setPositiveButton("OK") { _, _ ->
                // Handle the user's answer (e.g., save it to the database)
                currentQuestionIndex++
                showNextQuestion()
            }

            builder.setNegativeButton("Not OK") { _, _ ->
                // Handle the user's answer "Not OK" (e.g., show justification dialog)
                showJustificationDialog()
            }

            builder.show()
        } else {
            // All questions have been answered
            showCompletionButtons()
        }
    }

    fun showCompletionButtons() {
        completionButton1Visibility.value = View.VISIBLE
        completionButton2Visibility.value = View.VISIBLE
    }
}
