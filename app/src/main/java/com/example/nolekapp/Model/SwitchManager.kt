package com.example.nolekapp.Model

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.nolekapp.ViewModel.DynamicSwitchViewModel

class SwitchManager(private val context: Context, private val parentLayout: LinearLayout) {
    private val dynamicSwitchViewModel = DynamicSwitchViewModel()

    fun createSwitches(count: Int) {
        dynamicSwitchViewModel.createSwitches(count, context, parentLayout)
        Log.d("SwitchManager", "Created $count switches")
    }


    fun removeAllSwitches() {
        for (switch in dynamicSwitchViewModel.switchesList) {
            (switch.parent as? ViewGroup)?.removeView(switch)
        }
        Log.d("SwitchManager", "Removed all switches")
    }
}
