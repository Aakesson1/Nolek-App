package com.example.nolekapp.Database.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TestObject(
    val Name: String,
    val Description: String,
    val sniffing_point: Int,
    val Objecttype: String,
    @PrimaryKey(autoGenerate = true)
    val Serienumber_id: Int = 0
)
