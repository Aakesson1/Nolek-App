package com.example.nolekapp.Model

import com.example.nolekapp.Database.Data.TestResultat
import com.example.nolekapp.Model.SortType
import java.util.Date

data class TestResultatState(
    val testResultat: List<TestResultat> = emptyList(),
    val Name: String = "",
    val Description: String = "",
    val sniffing_point: String = "",
    val Objecttype: String = "",
    val Reason: String = "",
    val Status: String= "",
    val isAddingTestResultat: Boolean = false,
    val sortType: SortType = SortType.NAME
)