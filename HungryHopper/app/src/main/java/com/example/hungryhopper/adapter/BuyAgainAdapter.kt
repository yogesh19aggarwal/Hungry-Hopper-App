package com.example.hungryhopper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hungryhopper.databinding.BuyAgainBinding

class BuyAgainAdapter(private val buyAgainFoodName: ArrayList<String>, private val buyAgainFoodPrice: ArrayList<String>,
                      private val buyAgainFoodImage : ArrayList<Int>): RecyclerView.Adapter<BuyAgainAdapter.BuyAgainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyAgainViewHolder {
        return BuyAgainViewHolder(BuyAgainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return buyAgainFoodName.size
    }

    override fun onBindViewHolder(holder: BuyAgainViewHolder, position: Int) {
        holder.bind(buyAgainFoodName[position], buyAgainFoodPrice[position], buyAgainFoodImage[position])
    }

    inner class BuyAgainViewHolder(private val binding: BuyAgainBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(foodName: String, foodPrice: String, foodImage: Int) {

            binding.apply {
                buyAgainFood.text = foodName
                buyAgainPrice.text = foodPrice
                buyAgainImage.setImageResource(foodImage)
            }
        }


    }

}