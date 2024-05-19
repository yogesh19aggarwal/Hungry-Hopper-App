package com.example.adminhungryhopper.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adminhungryhopper.Models.AllMenu
import com.example.adminhungryhopper.databinding.ItemItemBinding
import com.google.firebase.database.DatabaseReference

class AllItemAdapter(
    private val context: Context,
    private val menuList: ArrayList<AllMenu>,
    private val databaseReference: DatabaseReference,
    private val onDeleteClickListener:(position: Int)->Unit
): RecyclerView.Adapter<AllItemAdapter.AddItemViewHolder>() {

    private val itemQuantities = IntArray(menuList.size){1}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return AddItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class AddItemViewHolder(private val binding: ItemItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                val menuItem = menuList[position]
                val uriString = menuItem.foodImage
                val uri = Uri.parse(uriString)

                foodNameTextView.text = menuItem.foodName
                pricetextView.text = menuItem.foodPrice
                Glide.with(context).load(uri).into(foodImageView)

                minusButton.setOnClickListener {
                    decreaseQuantity(position)
                }
                plusButton.setOnClickListener {
                    increaseQuantity(position)
                }
                deleteButton.setOnClickListener {
                    onDeleteClickListener(position)
                }
            }
        }

        private fun decreaseQuantity(position: Int){
            if(itemQuantities[position] > 1){
                itemQuantities[position]--
                binding.quantity.text = itemQuantities[position].toString()
            }
        }
        private fun increaseQuantity(position: Int){
            if(itemQuantities[position] < 10){
                itemQuantities[position]++
                binding.quantity.text = itemQuantities[position].toString()
            }
        }
        private fun deleteItem(position: Int){
            menuList.removeAt(position)
            menuList.removeAt(position)
            menuList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position, menuList.size)

        }

    }
}