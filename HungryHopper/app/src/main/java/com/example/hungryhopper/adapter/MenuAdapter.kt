package com.example.hungryhopper.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.hungryhopper.DetailsActivity
import com.example.hungryhopper.databinding.MenuItemBinding

class MenuAdapter(private val menuItemName : MutableList<String>, private val menuItemPrice : MutableList<String>, private val MenuImage: MutableList<Int>,
                  private val requireContext: Context):
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {


        private val itemClickListener: OnClickListener ?= null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.MenuViewHolder {
        return MenuViewHolder(MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MenuAdapter.MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return menuItemName.size
    }

    inner class MenuViewHolder(private val binding: MenuItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    itemClickListener?.onItemClick(position)
                }
                // setOnClickListerner to open details

                val intent = Intent(requireContext, DetailsActivity::class.java)
                intent.putExtra("MenuItemName", menuItemName[position])
                intent.putExtra("MenuItemImage", MenuImage[position])
                requireContext.startActivity(intent)
            }
        }
        fun bind(position: Int) {

            binding.apply{
                menuFoodName.text = menuItemName[position]
                menuPrice.text = menuItemPrice[position]
                menuImage.setImageResource(MenuImage[position])
            }
        }
    }
    interface OnClickListener{
        fun onItemClick(position: Int)

    }

}

