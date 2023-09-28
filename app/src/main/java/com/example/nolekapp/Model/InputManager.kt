package com.example.nolekapp.Model

import android.app.Activity
import android.app.AlertDialog
import android.text.InputFilter
import android.text.InputType
import android.widget.EditText
import android.widget.Toast

class InputManager(private val activity: Activity, private val switchManager: SwitchManager) {
    fun showSwitchCountDialog() {
        val inputEditText = EditText(activity)
        inputEditText.inputType = InputType.TYPE_CLASS_NUMBER
        val maxLength = 2 // Max længde for input (her er det 2 cifre)

        val inputFilterArray = arrayOfNulls<InputFilter>(1)
        inputFilterArray[0] = InputFilter.LengthFilter(maxLength)

        inputEditText.filters = inputFilterArray

        val alertDialog = AlertDialog.Builder(activity)
            .setTitle("Indtast antal skifter (maks 15)")
            .setView(inputEditText)
            .setPositiveButton("Opret") { dialog, _ ->
                val inputText = inputEditText.text.toString()
                if (inputText.isNotEmpty()) {
                    val switchCount = inputText.toInt()
                    if (switchCount <= 15) {
                        switchManager.createSwitches(switchCount)
                    } else {
                        // Vis en besked om, at værdien skal være 15 eller mindre
                        Toast.makeText(activity, "Maksimalt 15 skifter tilladt", Toast.LENGTH_SHORT).show()
                    }
                }
                dialog.dismiss()
            }
            .setNegativeButton("Annuller") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog.show()
    }
}

