package com.example.hungryhopper.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hungryhopper.Model.MenuItems
import com.example.hungryhopper.R
import com.example.hungryhopper.adapter.MenuAdapter
import com.example.hungryhopper.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MenuBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMenuBottomSheetBinding

    private lateinit var database: FirebaseDatabase
    private lateinit var menuItems: MutableList<MenuItems>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)

        binding.buttonBack.setOnClickListener {
            dismiss()
        }

        // dummy Data
//        val menuFoodName = listOf("Burger", "Sandwich", "Veg Momos", "item", "Paneer Sandwich", "Paneer Momos")
//        val menuPrice = listOf("$5", "$6", "$8", "$9", "$10", "$15")
//        val menuFoodImages = listOf(
//            R.drawable.menu1,
//            R.drawable.menu2,
//            R.drawable.menu3,
//            R.drawable.menu4,
//            R.drawable.menu5,
//            R.drawable.menu6,
//        )

        retrieveMenuItems()

        return binding.root
    }

    private fun retrieveMenuItems() {
        database = FirebaseDatabase.getInstance()
        val foodRef = database.reference.child("Menu")
        menuItems = mutableListOf()

        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSnapshot in snapshot.children) {
                    val menuItem = foodSnapshot.getValue(MenuItems::class.java)
                    menuItem?.let { menuItems.add(it) }
                }

                Log.d("Items", "onDataChange: Data Recieved")
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun setAdapter() {
        if (menuItems.isNotEmpty()) {
            val adapter = MenuAdapter(menuItems, requireContext())

            binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.menuRecyclerView.adapter = adapter

            Log.d("Items", "setAdapter: data set")
        } else{
            Log.d("Items", "setAdapter: data not set")
        }

    }

}