package com.example.adminhungryhopper

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminhungryhopper.Adapter.AllItemAdapter
import com.example.adminhungryhopper.Models.AllMenu
import com.example.adminhungryhopper.Utils.MENU_NODE
import com.example.adminhungryhopper.databinding.ActivityAllItemBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllItemActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private var menuItems: ArrayList<AllMenu> = ArrayList()

    private lateinit var binding: ActivityAllItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAllItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        databaseReference = FirebaseDatabase.getInstance().reference
        retrieveMenuItem()


        // dummy data
//        val menuFoodName = listOf("Burger", "Sandwich", "Veg Momos", "item", "Paneer Sandwich", "Paneer Momos")
//        val menuItemPrice = listOf("$5", "$6", "$8", "$9", "$10", "$15")
//        val menuImage = listOf(
//            R.drawable.menu1,
//            R.drawable.menu2,
//            R.drawable.menu3,
//            R.drawable.menu4,
//            R.drawable.menu5,
//            R.drawable.menu6
//        )



        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun retrieveMenuItem() {
        database = FirebaseDatabase.getInstance()
        val foodRef: DatabaseReference = database.reference.child(MENU_NODE)

        foodRef.addListenerForSingleValueEvent(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                //clear existing data
                menuItems.clear()

                for(foodSnapshot in snapshot.children){
                    val menuItem = foodSnapshot.getValue(AllMenu::class.java)
                    menuItem?.let {
                        menuItems.add(it)
                    }
                }

                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Database Error", "Error: ${error.message}")
            }
        })
    }

    private fun setAdapter() {
        val adapter = AllItemAdapter(this, menuItems, databaseReference)
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.menuRecyclerView.adapter = adapter
    }
}