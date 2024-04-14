package com.example.hungryhopper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hungryhopper.databinding.CartItemBinding

class CartAdapter(private val cartItems: MutableList<String>, private val cartItemsPrice: MutableList<String>, private val cartImageA: MutableList<Int>):
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

private val itemQuantities = IntArray(cartItems.size){1}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val image = cartImageA[position]
        val foodName = cartItems[position]
        val itemPrice = cartItemsPrice[position]

        holder.bind(foodName, itemPrice, image)
    }

    inner class CartViewHolder(private val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : String, price: String, image: Int){
            binding.apply {
                cartFoodName.text = item
                cartItemPrice.text = price
                cartImage.setImageResource(image)
                quantity.text = itemQuantities[position].toString()

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
            cartItems.removeAt(position)
            cartItemsPrice.removeAt(position)
            cartImageA.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position, cartItems.size)

        }
    }
}