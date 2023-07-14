package com.submission.suitmediatest.ui.thirdscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.suitmediatest.databinding.ActivityThirdScreenBinding
import androidx.activity.viewModels

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityThirdScreenBinding
    private val thirdScreenViewModel: ThirdScreenViewModel by viewModels {
        ViewModelFactory(this)
    }
    private lateinit var thirdScreenAdapter: ThirdScreenAdapter
    private val loadingStateAdapter = LoadingStateAdapter { thirdScreenAdapter.retry() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivIcBack.setOnClickListener { onBackPressed() }


        setupViewModel()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        thirdScreenAdapter = ThirdScreenAdapter()

        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(this@ThirdScreenActivity)
            adapter = thirdScreenAdapter.withLoadStateFooter(loadingStateAdapter)
        }
    }

    private fun setupViewModel() {

        thirdScreenViewModel.users.observe(this, {
            thirdScreenAdapter.submitData(lifecycle, it)
        })

    }
}