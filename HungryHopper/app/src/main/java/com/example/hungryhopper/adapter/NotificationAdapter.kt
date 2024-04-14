package com.example.hungryhopper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hungryhopper.databinding.NotifcationItemBinding

class NotificationAdapter(private var notification: ArrayList<String>, private var notificateImage: ArrayList<Int> ) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = NotifcationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notification.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class NotificationViewHolder(val binding: NotifcationItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.notificationTextView.text = notification[position]
            binding.notificationImageView.setImageResource(notificateImage[position])
        }

    }
}