package com.example.hungryhopper.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hungryhopper.databinding.BuyAgainBinding

class BuyAgainAdapter(
    private val buyAgainFoodName: MutableList<String>,
    private val buyAgainFoodPrice: MutableList<String>,
    private val buyAgainFoodImage: MutableList<String>,
    private val requireContext: Context
) :
    RecyclerView.Adapter<BuyAgainAdapter.BuyAgainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyAgainViewHolder {
        return BuyAgainViewHolder(
            BuyAgainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return buyAgainFoodName.size
    }

    override fun onBindViewHolder(holder: BuyAgainViewHolder, position: Int) {
        holder.bind(
            buyAgainFoodName[position],
            buyAgainFoodPrice[position],
            buyAgainFoodImage[position]
        )
    }

    inner class BuyAgainViewHolder(private val binding: BuyAgainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(foodName: String, foodPrice: String, foodImage: String) {

            binding.buyAgainFood.text = foodName
            binding.buyAgainPrice.text = foodPrice

            val image = foodImage
            val uri = Uri.parse(image)
            Glide.with(requireContext).load(uri).into(binding.buyAgainImage)
        }


    }

}