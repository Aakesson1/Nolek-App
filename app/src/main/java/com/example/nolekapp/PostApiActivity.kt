package com.example.nolekapp

import LeakTestApiService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.nolekapp.Database.Constants.BASE_URL2
import com.example.nolekapp.Database.Data.TestResultat
import com.example.nolekapp.Database.MongoDB
import com.example.nolekapp.Model.ObjectIdTypeAdapter
import com.google.gson.GsonBuilder
import org.mongodb.kbson.ObjectId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_api)
        val editTextstatus = findViewById<EditText>(R.id.editTextstatus)
        val editTextDescription = findViewById<EditText>(R.id.editTextDescription)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextObjectType = findViewById<EditText>(R.id.editTextobjectType)
        val editTextReason = findViewById<EditText>(R.id.editReason)
        val editTextsniffingPoint = findViewById<EditText>(R.id.editTextsniffingPoint)

        val btnAddTest = findViewById<Button>(R.id.buttonSubmit)
        btnAddTest.setOnClickListener {
            val newLeakTest = TestResultat().apply {
                ownerId = MongoDB.user?.id.toString()
                description = editTextDescription.text.toString()
                name = editTextName.text.toString()
                objectType = editTextObjectType.text.toString()
                reason = editTextReason.text.toString()
                sniffingPoint =editTextsniffingPoint.text.toString()
                status = editTextstatus.text.toString()
                // timestamp = Date()
            }
            addLeakTest(newLeakTest)
            editTextDescription.text.clear()
            editTextstatus.text.clear()
            editTextName.text.clear()
            editTextObjectType.text.clear()
            editTextReason.text.clear()
            editTextsniffingPoint.text.clear()
        }
        val backButton = findViewById<Button>(R.id.button_back_to_menu)
        backButton.setOnClickListener {
            goToMenuActivity()
        }
    }

    private fun goToMenuActivity() {
        finish()
    }



    }
    private fun addLeakTest(leakTest: TestResultat) {
        val gson = GsonBuilder()
            .registerTypeAdapter(ObjectId::class.java, ObjectIdTypeAdapter())
            .create()
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL2)
            .build()
            .create(LeakTestApiService::class.java)

        val retrofitData = retrofitBuilder.addLeakTest(leakTest)

        retrofitData.enqueue(object : Callback<TestResultat> {
            override fun onResponse(call: Call<TestResultat>, response: Response<TestResultat>) {
                if (response.isSuccessful) {
                    Log.d("ApiActivity", "Test result added: " + response.body())
                } else {
                    Log.d("ApiActivity", "Error occurred: " + response.errorBody())
                }
            }

            override fun onFailure(call: Call<TestResultat>, t: Throwable) {
                Log.d("ApiActivity", "onFailure: " + t.message)
            }
        })
    }
