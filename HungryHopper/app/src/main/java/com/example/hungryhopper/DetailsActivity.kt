package com.example.hungryhopper

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.hungryhopper.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private var foodName: String ?= null
    private var foodImage: String ?= null
    private var foodDescription: String ?= null
    private var foodIngredients: String ?= null
    private var foodPrice: String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodName = intent.getStringExtra("MenuItemName")
        foodImage = intent.getStringExtra("MenuItemImage")
        foodPrice = intent.getStringExtra("MenuItemPrice")
        foodDescription = intent.getStringExtra("MenuItemDescription")
        foodIngredients = intent.getStringExtra("MenuItemIngredients")


        with(binding){
            detailFoodName.text = foodName
            descriptiontextView.text = foodDescription
            ingredientsTextView.text = foodIngredients
            Glide.with(this@DetailsActivity).load(Uri.parse(foodImage)).into(binding.detailFoodImage)
        }


        binding.imageButton.setOnClickListener {
            finish()
        }
    }
}