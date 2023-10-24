package com.example.nolekapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

// ...

class DatabaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)



        val backbutton = findViewById<Button>(R.id.backButton)
        backbutton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)

        }
    }
}