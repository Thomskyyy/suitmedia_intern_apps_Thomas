package com.example.test_internship_thomaskevinsubowo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Screen3 : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var apiService: retrofit.ApiService

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen3)

        recyclerView = findViewById(R.id.UserRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val backButton : ImageButton = findViewById(R.id.backScreen2Button)

        backButton.setOnClickListener{
            val intent = Intent(this, Screen2::class.java)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(com.example.test_internship_thomaskevinsubowo.retrofit.ApiService::class.java)

        fetchUsers()
    }

    private fun fetchUsers() {
        apiService.getUsers(page = 1, perPage = 10).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { userResponse ->
                        // Initialize and set the adapter
                        userAdapter = UserAdapter(userResponse.data, this@Screen3)
                        recyclerView.adapter = userAdapter
                    }
                } else {
                    Toast.makeText(this@Screen3, "Failed to load data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@Screen3, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
