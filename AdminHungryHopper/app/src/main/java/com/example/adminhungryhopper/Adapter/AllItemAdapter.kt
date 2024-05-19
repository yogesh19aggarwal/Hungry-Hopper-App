package com.example.adminhungryhopper.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adminhungryhopper.Models.AllMenu
import com.example.adminhungryhopper.Utils.MENU_NODE
import com.example.adminhungryhopper.databinding.ItemItemBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllItemAdapter(
    private val context: Context,
    private val menuList: ArrayList<AllMenu>,
    private val databaseReference: DatabaseReference,
    private val onDeleteClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<AllItemAdapter.AddItemViewHolder>() {

    private val itemQuantities = IntArray(menuList.size) { 1 }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class AddItemViewHolder(private val binding: ItemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
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

                    getUniqueAtPosition(position) { uniqueKey ->
                        if (uniqueKey != null) {
                            deleteItem(position, uniqueKey)
                        }
                    }
//                    onDeleteClickListener(position)
                }
            }
        }

        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                binding.quantity.text = itemQuantities[position].toString()
            }
        }

        private fun increaseQuantity(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                binding.quantity.text = itemQuantities[position].toString()
            }
        }

        private fun deleteItem(position: Int, uniqueKey: String) {

            val database = FirebaseDatabase.getInstance()
            val menuRef = database.reference.child(MENU_NODE).child(uniqueKey).removeValue()
                .addOnSuccessListener {
                    menuList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(0, menuList.size)
                    Toast.makeText(context, "Item Deleted Successfuly", Toast.LENGTH_SHORT).show()
                }
//            menuList.removeAt(position)
//            menuList.removeAt(position)
//            menuList.removeAt(position)
            notifyItemRangeRemoved(position, menuList.size)

        }

    }

    private fun getUniqueAtPosition(position: Int, onComplete: (String?) -> Unit) {

        val database = FirebaseDatabase.getInstance()

        val menuRef = database.reference.child(MENU_NODE)
        menuRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var uniqueKey: String? = null

                snapshot.children.forEachIndexed { index, dataSnapshot ->

                    if (index == position) {
                        uniqueKey = dataSnapshot.key
                        return@forEachIndexed
                    }
                }
                onComplete(uniqueKey)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}