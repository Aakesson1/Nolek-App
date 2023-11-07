package com.example.nolekapp

import LeakTestApiService
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.compose.setContent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nolekapp.Database.Data.LeakTestData
import com.example.nolekapp.Model.ApiAdapter
import com.example.nolekapp.View.NolekAppMenu
import com.example.nolekapp.ui.theme.NolekAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
const val BASE_URL = "https://eu-central-1.aws.data.mongodb-api.com/app/testapplication-ischo/endpoint/"
class ApiActivity : AppCompatActivity() {

    lateinit var apiAdapter: ApiAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*
        setContent {
            NolekAppTheme {
                ApiActivity()
            }

 */

            setContentView(R.layout.activity_api)

            val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_testresults)
            recyclerView.setHasFixedSize(true)
            linearLayoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = linearLayoutManager

            // Initialiser adapteren med en tom liste
            apiAdapter = ApiAdapter(baseContext, emptyList())
            recyclerView.adapter = apiAdapter
            getMyData()
            val backButton = findViewById<Button>(R.id.button_back_to_menu)
            backButton.setOnClickListener {
                goToMenuActivity()
            }
        }

        private fun goToMenuActivity() {
            finish()
        }

        private fun getMyData() {
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(LeakTestApiService::class.java)

            val retrofitData = retrofitBuilder.getAllLeakTests()

            retrofitData.enqueue(object : Callback<List<LeakTestData>?> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<List<LeakTestData>?>,
                    response: Response<List<LeakTestData>?>
                ) {
                    val responseBody = response.body()!!
                    apiAdapter = ApiAdapter(baseContext, responseBody)
                    apiAdapter.notifyDataSetChanged()
                    findViewById<RecyclerView>(R.id.recyclerview_testresults).adapter = apiAdapter
                }

                override fun onFailure(call: Call<List<LeakTestData>?>, t: Throwable) {
                    Log.d("ApiActivity", "onFailure" + t.message)
                }
            })
        }
    }
