package com.submission.suitmediatest.ui.firstscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.submission.suitmediatest.databinding.ActivityMainBinding
import com.submission.suitmediatest.ui.secondscreen.SecondScreenActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener { checkPalindrome() }

        binding.btnNext.setOnClickListener { next() }
    }

    private fun checkPalindrome() {
        val inputWord = binding.inputWord.text.toString().trim()
        if (inputWord.isNotEmpty()) {
            val word = inputWord.replace("\\s+".toRegex(), "").toLowerCase()
            val reverseWord = word.reversed()
            val isPalindrome = word == reverseWord

            val message = if (isPalindrome) {
                "isPalindrome"
            } else {
                "notPalindrome"
            }

            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@MainActivity, "Please enter a word", Toast.LENGTH_SHORT).show()
        }
    }


    private fun next() {
        val inputName = binding.inputName.text.toString()
        val intent = Intent(this, SecondScreenActivity::class.java)
        intent.putExtra(SecondScreenActivity.EXTRA_NAME, inputName)
        startActivity(intent)
    }
}