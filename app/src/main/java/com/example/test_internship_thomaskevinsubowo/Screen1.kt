package com.example.test_internship_thomaskevinsubowo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Screen1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen1)

        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val palindromeEditText: EditText = findViewById(R.id.palindromeEditText)
        val checkButton: Button = findViewById(R.id.checkButton)
        val nextButton: Button = findViewById(R.id.nextButton)

        checkButton.setOnClickListener {
            val text = palindromeEditText.text.toString()
            if (text == text.reversed()) {
                Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "not palindrome", Toast.LENGTH_SHORT).show()
            }
        }

        nextButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val intent = Intent(this, Screen2::class.java)
            intent.putExtra("name", name)
            startActivity(intent)
        }
    }
}