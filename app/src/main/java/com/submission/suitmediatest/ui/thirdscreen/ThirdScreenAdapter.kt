package com.submission.suitmediatest.ui.thirdscreen

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.submission.suitmediatest.data.Users
import com.submission.suitmediatest.databinding.ItemUserBinding
import com.submission.suitmediatest.ui.secondscreen.SecondScreenActivity

class ThirdScreenAdapter : PagingDataAdapter<Users, ThirdScreenAdapter.ThirdScreenViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: ThirdScreenAdapter.ThirdScreenViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThirdScreenAdapter.ThirdScreenViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThirdScreenViewHolder(binding)
    }

    class ThirdScreenViewHolder(private val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user : Users) {
            Glide.with(binding.root)
                .load(user.avatar)
                .into(binding.photo)
            binding.firstName.text = user.firstName
            binding.lastName.text = user.lastName
            binding.email.text = user.email

            binding.root.setOnClickListener{
                val intent = Intent(it.context, SecondScreenActivity::class.java)
                intent.putExtra(SecondScreenActivity.EXTRA_FIRST_NAME, user.firstName)
                intent.putExtra(SecondScreenActivity.EXTRA_LAST_NAME, user.lastName)
                it.context.startActivity(intent)
            }
        }
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Users>() {
            override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }


}