package com.example.hungryhopper.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hungryhopper.Utils.CARTITEM_NODE
import com.example.hungryhopper.Utils.USER_NODE
import com.example.hungryhopper.databinding.CartItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartAdapter(
    private val cartItems: MutableList<String>,
    private val cartItemsPrice: MutableList<String>,
    private val cartDescriptions: MutableList<String>,
    private val cartImages: MutableList<String>,
    private val cartQuantity: MutableList<Int>,
    private val cartIngredients: MutableList<String>,
    private val context: Context
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val auth = FirebaseAuth.getInstance()

    init {
        val database = FirebaseDatabase.getInstance()
        val userId = auth.currentUser?.uid ?: ""
        val cartItemNumber = cartItems.size

        itemQuantities = IntArray(cartItemNumber) { 1 }
        cartItemsReference = database.reference.child(USER_NODE).child(userId).child(CARTITEM_NODE)
    }

    companion object {
        private var itemQuantities = intArrayOf()
        private lateinit var cartItemsReference: DatabaseReference
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            CartItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class CartViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                cartFoodName.text = cartItems[position]
                cartItemPrice.text = cartItemsPrice[position]
                quantity.text = itemQuantities[position].toString()

                val uriString = cartImages[position]
                val uri = Uri.parse(uriString.toString())

                Glide.with(context).load(uri).into(cartImage)

                minusButton.setOnClickListener {
                    decreaseQuantity(position)
                }
                plusButton.setOnClickListener {
                    increaseQuantity(position)
                }
                deleteButton.setOnClickListener {
                    val itemPosition = adapterPosition
                    if (itemPosition != RecyclerView.NO_POSITION) {
                        deleteItem(position)
                    }
                }
            }
        }

        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                cartQuantity[position] = itemQuantities[position]
                binding.quantity.text = itemQuantities[position].toString()
            }
        }

        private fun increaseQuantity(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                cartQuantity[position] = itemQuantities[position]
                binding.quantity.text = itemQuantities[position].toString()
            }
        }

        private fun deleteItem(position: Int) {

            val positionRetrieve = position

            getUniqueAtPosition(positionRetrieve) { uniqueKey ->
                if (uniqueKey != null) {
                    removeItem(position, uniqueKey)
                }
            }
        }
    }

    private fun removeItem(position: Int, uniqueKey: String) {

        if (uniqueKey != null) {
            cartItemsReference.child(uniqueKey).removeValue().addOnSuccessListener {
                cartItems.removeAt(position)
                cartImages.removeAt(position)
                cartDescriptions.removeAt(position)
                cartQuantity.removeAt(position)
                cartItemsPrice.removeAt(position)
                cartIngredients.removeAt(position)

                itemQuantities =
                    itemQuantities.filterIndexed { index, i -> index != position }.toIntArray()
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, cartItems.size)
                Toast.makeText(context, " Item Deleted", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getUniqueAtPosition(i: Int, onComplete: (String?) -> Unit) {
        cartItemsReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var uniqueKey: String? = null

                snapshot.children.forEachIndexed { index, dataSnapshot ->
                    if (index == i) {
                        uniqueKey = dataSnapshot.key
                        return@forEachIndexed
                    }
                }

                onComplete(uniqueKey)
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun getUpdatedItemQuantities(): MutableList<Int> {
        val itemQuantity = mutableListOf<Int>()
        itemQuantity.addAll(cartQuantity)
        return itemQuantity
    }
}