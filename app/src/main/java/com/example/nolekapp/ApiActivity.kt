package com.example.nolekapp

import LeakTestApiService
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.privacysandbox.tools.core.model.Type
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nolekapp.Database.Constants.BASE_URL
import com.example.nolekapp.Database.Constants.BASE_URL2
import com.example.nolekapp.Database.Data.LeakTestData
import com.example.nolekapp.Database.Data.TestResultat
import com.example.nolekapp.Model.ApiAdapter
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Proxy
import java.net.InetSocketAddress
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Base64


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

    @SuppressLint("SuspiciousIndentation")
    private fun getMyData() {


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL2)
            .build()

        val service = retrofit.create(LeakTestApiService::class.java)
        val retrofitData = service.getAllLeakTests()
            retrofitData.enqueue(object : Callback<List<LeakTestData>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<List<LeakTestData>?>,
                    response: Response<List<LeakTestData>?>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val responseBody = response.body()!!
                        apiAdapter = ApiAdapter(baseContext, responseBody)
                        apiAdapter.notifyDataSetChanged()
                        findViewById<RecyclerView>(R.id.recyclerview_testresults).adapter = apiAdapter
                    } else {
                        Log.d("ApiActivity", "Response not successful or body is null")
                    }
                }




                override fun onFailure(call: Call<List<LeakTestData>?>, t: Throwable) {
                    Log.d("ApiActivity", "onFailure" + t.message)

                }
            })
        }

    }

