package com.example.hungryhopper.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hungryhopper.DetailsActivity
import com.example.hungryhopper.databinding.PopularItemListBinding

class PopularAdapter(
    private val items: List<String>,
    private val image: List<Int>,
    private val price: List<String>,
    private val requireContext: Context
) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(
            PopularItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = items[position]
        val images = image[position]
        val price = price[position]
        holder.bind(item, images, price)

        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext, DetailsActivity::class.java)
            intent.putExtra("MenuItemName", items[position])
            intent.putExtra("MenuItemImage", image[position])
            requireContext.startActivity(intent)
        }
    }

    class PopularViewHolder(private val binding: PopularItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, image: Int, price: String) {
            binding.menuFoodName.text = item
            binding.menuImage.setImageResource(image)
            binding.menuPrice.text = price
        }
    }
}