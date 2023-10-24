package com.example.nolekapp.ViewModel

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Switch
import androidx.lifecycle.ViewModel
import com.example.nolekapp.R

class DynamicSwitchViewModel() : ViewModel() {
    val switchesList = mutableListOf<Switch>()
    private lateinit var parentLayout: LinearLayout


    fun createSwitches(count: Int, context: Context, parentLayout: LinearLayout) {
        removeAllSwitches() // Fjern eventuelle eksisterende skifter

        val padding = context.resources.getDimensionPixelSize(R.dimen.switch_padding)

        for (i in 1..count) {
            val switch = Switch(context)
            val letter = ('a' + i - 1).toString()  // Konverterer til bogstav (a, b, c, ...)
            switch.text = letter

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(130, padding, 0, 8)
            switch.layoutParams = layoutParams

            switchesList.add(switch)
            parentLayout.addView(switch)
        }
        Log.d("DynamicSwitchViewModel", "Created $count switches")
        println(switchesList.size)
    }
    fun setAllSwitchesToTrue() {
        switchesList.forEach { it.isChecked = true }
        parentLayout.invalidate() // Opdaterer UI'en
    }

    fun setAllSwitchesToFalse() {
        switchesList.forEach { it.isChecked = false }
        parentLayout.invalidate() // Opdaterer UI'en
    }
    private fun removeAllSwitches() {
        switchesList.forEach { switch ->
            (switch.parent as? ViewGroup)?.removeView(switch)
        }
        switchesList.clear()
        Log.d("DynamicSwitchViewModel", "Removed all switches")
    }


}
