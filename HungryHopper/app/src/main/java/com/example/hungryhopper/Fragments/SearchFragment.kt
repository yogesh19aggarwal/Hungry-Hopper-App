package com.example.hungryhopper.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hungryhopper.Model.MenuItems
import com.example.hungryhopper.Utils.MENU_NODE
import com.example.hungryhopper.adapter.MenuAdapter
import com.example.hungryhopper.databinding.FragmentSearchBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter

    private lateinit var database: FirebaseDatabase
    private val originalMenuItems = mutableListOf<MenuItems>()


//        dummy data
//    private val originalMenuFoodName = listOf("Burger", "Sandwich", "Veg Momos", "item", "Paneer Sandwich", "Paneer Momos")
//    val originalMenuPrice = listOf("$5", "$6", "$8", "$9", "$10", "$15")
//    val originalMenuFoodImages = listOf(
//        R.drawable.menu1,
//        R.drawable.menu2,
//        R.drawable.menu3,
//        R.drawable.menu4,
//        R.drawable.menu5,
//        R.drawable.menu6,
//    )

//    val filterMenuFoodName = mutableListOf<String>()
//    val filterMenuItemPrice = mutableListOf<String>()
//    val filterMenuImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        //retrieve menu item from database
        retrieveMenuItem()

        //setup for search view
        setUpSearchView()

        return binding.root
    }

    private fun retrieveMenuItem() {

        database = FirebaseDatabase.getInstance()

        val foodRef = database.reference.child(MENU_NODE)

        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (foodSnapshot in snapshot.children) {

                    val menuItem = foodSnapshot.getValue(MenuItems::class.java)
                    menuItem?.let {
                        originalMenuItems.add(it)
                    }
                }

                showAllMenu()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun showAllMenu() {

        val filterMenuItem = ArrayList(originalMenuItems)
        setAdapter(filterMenuItem)
    }

    private fun setAdapter(filterMenuItem: List<MenuItems>) {
        adapter = MenuAdapter(filterMenuItem, requireContext())
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRecyclerView.adapter = adapter
    }

//    @SuppressLint("NotifyDataSetChanged")
//    private fun showAllMenu() {
//        filterMenuFoodName.clear()
//        filterMenuItemPrice.clear()
//        filterMenuImage.clear()
//
//
//        filterMenuFoodName.addAll(originalMenuFoodName)
//        filterMenuItemPrice.addAll(originalMenuPrice)
//        filterMenuImage.addAll(originalMenuFoodImages)
//
//        adapter.notifyDataSetChanged()
//
//    }

    private fun setUpSearchView() {

        binding.searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText)
                return true
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterMenuItems(query: String) {
//        filterMenuFoodName.clear()
//        filterMenuItemPrice.clear()
//        filterMenuImage.clear()
//
//        originalMenuFoodName.forEachIndexed { index, foodName ->
//            if(foodName.contains(query, ignoreCase = true)){
//                filterMenuFoodName.add(foodName)
//                filterMenuItemPrice.add(originalMenuPrice[index])
//                filterMenuImage.add(originalMenuFoodImages[index])
//            }
//
//        }

        val filterMenuItem = originalMenuItems.filter {
            it.foodName?.contains(query, ignoreCase = true) == true
        }
        setAdapter(filterMenuItem)
    }

}