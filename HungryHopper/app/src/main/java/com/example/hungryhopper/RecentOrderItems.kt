package com.example.hungryhopper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hungryhopper.Model.OrderDetails
import com.example.hungryhopper.adapter.RecentBuyAdapter
import com.example.hungryhopper.databinding.ActivityRecentOrderItemsBinding

class RecentOrderItems : AppCompatActivity() {

    private lateinit var binding: ActivityRecentOrderItemsBinding
    private lateinit var allFoodNames :ArrayList<String>
    private lateinit var allFoodImages :ArrayList<String>
    private lateinit var allFoodPrices :ArrayList<String>
    private lateinit var allFoodQuantities :ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRecentOrderItemsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recentOrderItems = intent.getSerializableExtra("RecentOrderItem") as ArrayList<OrderDetails>

        binding.backButton.setOnClickListener {
            finish()
        }

        recentOrderItems.let {
                orderDetails ->
            if(orderDetails.isNotEmpty()){
                val recentOrderItem = orderDetails[0]

                allFoodNames = recentOrderItem.foodName as ArrayList<String>
                allFoodImages = recentOrderItem.foodImage as ArrayList<String>
                allFoodPrices = recentOrderItem.foodPrices as ArrayList<String>
                allFoodQuantities = recentOrderItem.foodQuantity as ArrayList<Int>
            }
        }

        setAdapter()
    }

    private fun setAdapter() {
        val rv = binding.recyclerViewRecent

        rv.layoutManager = LinearLayoutManager(this)
        val adapter = RecentBuyAdapter(this, allFoodNames, allFoodImages, allFoodPrices, allFoodQuantities)

        rv.adapter = adapter
    }
}