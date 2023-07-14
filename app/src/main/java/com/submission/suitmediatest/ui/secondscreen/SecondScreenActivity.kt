package com.submission.suitmediatest.ui.secondscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.submission.suitmediatest.R
import com.submission.suitmediatest.databinding.ActivitySecondScreenBinding
import com.submission.suitmediatest.ui.thirdscreen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.second_screen)

        val name = intent.getStringExtra(EXTRA_NAME)
        val firstname = intent.getStringExtra(EXTRA_FIRST_NAME)
        val lastname = intent.getStringExtra(EXTRA_LAST_NAME)

        binding.tvName.text = name
        if (firstname != null && lastname != null) {
            val fullName = "$firstname $lastname"
            binding.tvSelectedUser.text = fullName
        }

        binding.ivIcBack.setOnClickListener { onBackPressed() }
        binding.btnChooseUser.setOnClickListener { toThirdScreen() }

    }

    private fun toThirdScreen() {
        val name = binding.tvName.text.toString()

        val intent = Intent(this, ThirdScreenActivity::class.java)
        intent.putExtra(EXTRA_NAME, name)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_FIRST_NAME = "extra_first_name"
        const val EXTRA_LAST_NAME = "extra_last_name"
    }
}