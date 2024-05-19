package com.example.adminhungryhopper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminhungryhopper.Adapter.OrderDetailsAdapter
import com.example.adminhungryhopper.Models.OrderDetails
import com.example.adminhungryhopper.databinding.ActivityOrderDetailsBinding

class OrderDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderDetailsBinding

    private var userName: String ?= null
    private var address: String ?= null
    private var phoneNumber: String ?= null
    private var totalPrice: String ?= null

    private var foodNames: ArrayList<String> = arrayListOf()
    private var foodImages: ArrayList<String> = arrayListOf()
    private var foodQuantity: ArrayList<Int> = arrayListOf()
    private var foodPrices: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.backToCart.setOnClickListener {
            finish()
        }

        getDataFromIntent()

    }

    private fun getDataFromIntent() {
        var receivedOrderDetails = intent.getSerializableExtra("UserOrderDetails") as OrderDetails

        receivedOrderDetails.let {
                orderDetails ->
            userName = receivedOrderDetails.userName
            foodNames = receivedOrderDetails.foodName as ArrayList<String>
            foodImages = receivedOrderDetails.foodImage as ArrayList<String>
            foodQuantity = receivedOrderDetails.foodQuantity as ArrayList<Int>
            address = receivedOrderDetails.address
            phoneNumber = receivedOrderDetails.phoneNumber
            foodPrices = receivedOrderDetails.foodPrices as ArrayList<String>
            totalPrice = receivedOrderDetails.totalPrice

            setUserDetails()
            setAdapter()

        }


    }

    private fun setAdapter() {
        var rv = binding.orderDetailRecyclerView

        rv.layoutManager = LinearLayoutManager(this)
        val adapter = OrderDetailsAdapter(this, foodNames, foodImages, foodQuantity, foodPrices)
        rv.adapter = adapter
    }

    private fun setUserDetails() {
        binding.name.text = userName
        binding.address.text = address
        binding.phoneNumber.text = phoneNumber
        binding.totalPrice.text = totalPrice
    }
}