package com.example.adminhungryhopper.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminhungryhopper.databinding.ItemItemBinding

class AllItemAdapter(private val MenuItemName: ArrayList<String>, private val MenuItemPrice: ArrayList<String>, private val MenuItemimage: ArrayList<Int>,):
    RecyclerView.Adapter<AllItemAdapter.AddItemViewHolder>() {

    private val itemQuantities = IntArray(MenuItemName.size){1}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return AddItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return MenuItemName.size
    }

    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class AddItemViewHolder(private val binding: ItemItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                foodNameTextView.text = MenuItemName[position]
                pricetextView.text = MenuItemPrice[position]
                foodImageView.setImageResource(MenuItemimage[position])

                minusButton.setOnClickListener {
                    decreaseQuantity(position)
                }
                plusButton.setOnClickListener {
                    increaseQuantity(position)
                }
                deleteButton.setOnClickListener {
                    val itemPosition = adapterPosition
                    if(itemPosition != RecyclerView.NO_POSITION){
                        deleteItem(position)
                    }
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
            MenuItemName.removeAt(position)
            MenuItemPrice.removeAt(position)
            MenuItemimage.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position, MenuItemName.size)

        }

    }
}