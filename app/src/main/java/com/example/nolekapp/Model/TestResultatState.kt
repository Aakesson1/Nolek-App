package com.example.nolekapp.Model

import com.example.nolekapp.Database.Data.TestResultat
import com.example.nolekapp.Model.SortType

data class TestResultatState(
    val testResultat: List<TestResultat> = emptyList(),
    val Name: String = "",
    val Description: String = "",
    val sniffing_point: String = "",
    val Objecttype: String = "",
    val isAddingTestResultat: Boolean = false,
    val sortType: SortType = SortType.NAME
)
