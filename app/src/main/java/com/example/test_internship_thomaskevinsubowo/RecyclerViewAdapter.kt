package com.example.test_internship_thomaskevinsubowo
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.bumptech.glide.Glide

class RecyclerViewAdapter : AppCompatActivity(){
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var apiService: retrofit.ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen3)

        recyclerView = findViewById(R.id.UserRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/users?page=1&per_page=10/")
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
                        userAdapter = UserAdapter(
                            userResponse.data,
                            context = TODO()
                        )
                        recyclerView.adapter = userAdapter
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}

class UserAdapter (private val users: List<User>, private val context: Context) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val email: TextView = itemView.findViewById(R.id.email)
        val avatar: ImageView = itemView.findViewById(R.id.avatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_cardview, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.name.text = "${user.first_name} ${user.last_name}"
        holder.email.text = user.email
        Glide.with(holder.itemView.context).load(user.avatar).into(holder.avatar)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, Screen2::class.java).apply {
                putExtra("names", "${user.first_name} ${user.last_name}")
            }
            context.startActivity(intent)
        }
    }


    override fun getItemCount() = users.size
}
