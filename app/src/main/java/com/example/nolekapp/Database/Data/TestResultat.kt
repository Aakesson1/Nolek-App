package com.example.nolekapp.Database.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TestResultat(
    val Name: String,
    val Description: String,
    val sniffing_point: String,
    val Objecttype: String,
    @PrimaryKey(autoGenerate = true)
    val Serienumber_id: Int = 0
)
