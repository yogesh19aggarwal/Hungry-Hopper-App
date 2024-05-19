package com.example.adminhungryhopper.Adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminhungryhopper.databinding.DeliveryItemBinding

class DeliveryAdapter(private var customerList: MutableList<String>, private var moneyStaus: MutableList<Boolean>): RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        var binding = DeliveryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeliveryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class DeliveryViewHolder(private val binding: DeliveryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                customerName.text = customerList[position]
                if(moneyStaus[position] == true){
                    payment.text = "Recieved"
                }else{
                    payment.text = "Not Recieved"
                }

                val colorMap = mapOf(
                    true to Color.GREEN, false to Color.RED
                )

                payment.setTextColor(colorMap[moneyStaus[position]]?:Color.BLACK)
                orderStatus.backgroundTintList = ColorStateList.valueOf(colorMap[moneyStaus[position]]?:Color.BLACK)
            }
        }

    }
}