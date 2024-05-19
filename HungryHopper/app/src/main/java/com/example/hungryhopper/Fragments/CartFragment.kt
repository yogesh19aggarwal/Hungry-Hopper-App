package com.example.hungryhopper.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hungryhopper.Model.CartItems
import com.example.hungryhopper.PayOutActivity
import com.example.hungryhopper.Utils.CARTITEM_NODE
import com.example.hungryhopper.Utils.MENU_NODE
import com.example.hungryhopper.Utils.USER_NODE
import com.example.hungryhopper.adapter.CartAdapter
import com.example.hungryhopper.databinding.FragmentCartBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    private lateinit var foodName: MutableList<String>
    private lateinit var foodPrice: MutableList<String>
    private lateinit var foodDescription: MutableList<String>
    private lateinit var foodImageUri: MutableList<String>
    private lateinit var foodIngredients: MutableList<String>
    private lateinit var quantity: MutableList<Int>
    private lateinit var cartAdapter: CartAdapter
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)


        // dummy data
//        val cartFood = listOf("Burger", "Sandwich", "Veg Momos", "item", "Paneer Sandwich", "Paneer Momos")
//        val cartItemPrice = listOf("$5", "$6", "$8", "$9", "$10", "$15")
//        val cartImage = listOf(
//            R.drawable.menu1,
//            R.drawable.menu2,
//            R.drawable.menu3,
//            R.drawable.menu4,
//            R.drawable.menu5,
//            R.drawable.menu6
//        )

        auth = FirebaseAuth.getInstance()

        retrieveCartItems()



        binding.proceed.setOnClickListener {
            getOrderItemsDetail()
        }
        return binding.root
    }

    private fun getOrderItemsDetail() {

        val orderIdRef = database.reference.child(USER_NODE).child(userId).child(CARTITEM_NODE)

        val foodName = mutableListOf<String>()
        val foodPrice = mutableListOf<String>()
        val foodDescription = mutableListOf<String>()
        val foodImage = mutableListOf<String>()
        val foodIngredient = mutableListOf<String>()

        val foodQuantities = cartAdapter.getUpdatedItemQuantities()

        orderIdRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (foodSnapshot in snapshot.children) {
                    val orderItems = foodSnapshot.getValue(CartItems::class.java)
                    orderItems?.foodName?.let { foodName.add(it) }
                    orderItems?.foodPrice?.let { foodPrice.add(it) }
                    orderItems?.foodDescription?.let { foodDescription.add(it) }
                    orderItems?.foodImage?.let { foodImage.add(it) }
                    orderItems?.foodIngredient?.let { foodIngredient.add(it) }
                }

                orderNow(
                    foodName,
                    foodPrice,
                    foodDescription,
                    foodImage,
                    foodIngredient,
                    foodQuantities
                )
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    requireContext(),
                    "Order making failed. Please try again!!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun orderNow(
        foodName: MutableList<String>,
        foodPrice: MutableList<String>,
        foodDescription: MutableList<String>,
        foodImage: MutableList<String>,
        foodIngredient: MutableList<String>,
        foodQuantities: MutableList<Int>
    ) {
        if (isAdded && context != null) {
            val intent = Intent(requireContext(), PayOutActivity::class.java)

            intent.putExtra("FoodItemName", foodName as ArrayList<String>)
            intent.putExtra("FoodItemPrice", foodPrice as ArrayList<String>)
            intent.putExtra("FoodItemDescription", foodDescription as ArrayList<String>)
            intent.putExtra("FoodItemImage", foodImage as ArrayList<String>)
            intent.putExtra("FoodItemIngredients", foodIngredient as ArrayList<String>)
            intent.putExtra("FoodItemQuantity", foodQuantities as ArrayList<Int>)

            startActivity(intent)

        }
    }

    private fun retrieveCartItems() {
        database = FirebaseDatabase.getInstance()
        userId = auth.currentUser?.uid ?: ""
        val foodRef = database.reference.child(USER_NODE).child(userId).child(CARTITEM_NODE)

        foodName = mutableListOf()
        foodPrice = mutableListOf()
        foodDescription = mutableListOf()
        foodImageUri = mutableListOf()
        foodIngredients = mutableListOf()
        quantity = mutableListOf()

        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (foodSnapshot in snapshot.children) {

                    val cartItems = foodSnapshot.getValue(CartItems::class.java)

                    cartItems?.foodName?.let { foodName.add(it) }
                    cartItems?.foodPrice?.let { foodPrice.add(it) }
                    cartItems?.foodDescription?.let { foodDescription.add(it) }
                    cartItems?.foodImage?.let { foodImageUri.add(it) }
                    cartItems?.foodQuantity?.let { quantity.add(it) }
                    cartItems?.foodIngredient?.let { foodIngredients.add(it) }
                }

                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Data not fetched", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setAdapter() {
        cartAdapter = CartAdapter(
            foodName,
            foodPrice,
            foodDescription,
            foodImageUri,
            quantity,
            foodIngredients,
            requireContext()
        ){
            position->
            deleteCartItem(position)
        }
        binding.cartRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.cartRecyclerView.adapter = cartAdapter
    }

    private fun deleteCartItem(position: Int) {

        var foodRef = database.reference.child(USER_NODE).child(MENU_NODE)

        foodRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for(foodSnapshot in snapshot.children){
                    var itemDelKey = foodSnapshot.key
                    foodRef = database.reference.child(USER_NODE).child(MENU_NODE).child(itemDelKey!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        foodRef.removeValue().addOnCompleteListener {
            task->

            if(task.isSuccessful){
                foodName.removeAt(position)
                foodPrice.removeAt(position)
                foodDescription.removeAt(position)
                foodImageUri.removeAt(position)
                quantity.removeAt(position)
                foodIngredients.removeAt(position)

                binding.cartRecyclerView.adapter?.notifyItemRemoved(position)
            } else{
                Toast.makeText(requireContext(), "Item Not Deleted", Toast.LENGTH_SHORT).show()
            }
        }

    }

}