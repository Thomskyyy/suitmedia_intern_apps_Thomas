package com.example.test_internship_thomaskevinsubowo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class Screen2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen2)

        val backScreen1Button : ImageButton = findViewById(R.id.backScreen1Button)
        val chooseUserButton : Button = findViewById(R.id.chooseUserbutton)
        val textView3: TextView = findViewById(R.id.textView3)
        val textView4: TextView = findViewById(R.id.textView4)

        val name = intent.getStringExtra("name")
        val names = intent.getStringExtra("names")
        textView3.text = name

        textView4.text = names

        backScreen1Button.setOnClickListener{
            val intent = Intent(this, Screen1::class.java)
            startActivity(intent)
        }

        chooseUserButton.setOnClickListener {
            val intent = Intent(this, Screen3::class.java)
            startActivity(intent)
        }
    }
}