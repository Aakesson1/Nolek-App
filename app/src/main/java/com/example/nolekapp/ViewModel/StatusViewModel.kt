package com.example.nolekapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StatusViewModel(application: Application) : AndroidViewModel(application) {

    private val _currentQuestion = MutableLiveData<String>()
    val currentQuestion: LiveData<String> get() = _currentQuestion

    private val QUESTIONS = ('a'..'o').mapIndexed { index, letter ->
        "Sniffing point ${index + 1}: $letter"
    }
    private var currentQuestionIndex = 0
    private var selectedQuestions: List<String> = QUESTIONS

    fun setQuestionsBasedOnPoints(points: Int) {
        if (points in 1..QUESTIONS.size) {
            selectedQuestions = QUESTIONS.take(points)
            currentQuestionIndex = 0
            showNextQuestion()
        } else {
            throw IllegalArgumentException("Invalid points value. It should be between 1 and ${QUESTIONS.size}.")
        }
    }

    fun showNextQuestion() {
        _currentQuestion.value = getNextQuestion()
    }

    private fun getNextQuestion(): String? {
        return if (currentQuestionIndex < selectedQuestions.size) {
            selectedQuestions[currentQuestionIndex++]
        } else null
    }
}
