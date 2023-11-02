package com.example.nolekapp.Model

import com.example.nolekapp.Database.Data.TestResultat
import com.example.nolekapp.Model.SortType

data class TestResultatState(
    val testResultat: List<TestResultat>? = null,
    val Name: String? = null,
    val Description: String? = null,
    val sniffing_point: String? = null,
    val Objecttype: String? = null,
    val Reason: String? = null,
    val Status: String? = null,
    val isAddingTestResultat: Boolean = false,
    val sortType: SortType = SortType.NAME
)