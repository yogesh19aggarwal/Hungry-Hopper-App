package com.example.hungryhopper

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.hungryhopper.Model.CartItems
import com.example.hungryhopper.Utils.CARTITEM_NODE
import com.example.hungryhopper.Utils.USER_NODE
import com.example.hungryhopper.databinding.ActivityDetailsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private lateinit var auth: FirebaseAuth

    private var foodName: String? = null
    private var foodImage: String? = null
    private var foodDescription: String? = null
    private var foodIngredients: String? = null
    private var foodPrice: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        foodName = intent.getStringExtra("MenuItemName")
        foodImage = intent.getStringExtra("MenuItemImage")
        foodPrice = intent.getStringExtra("MenuItemPrice")
        foodDescription = intent.getStringExtra("MenuItemDescription")
        foodIngredients = intent.getStringExtra("MenuItemIngredients")


        with(binding) {
            detailFoodName.text = foodName
            descriptiontextView.text = foodDescription
            ingredientsTextView.text = foodIngredients
            Glide.with(this@DetailsActivity).load(Uri.parse(foodImage))
                .into(binding.detailFoodImage)
        }


        binding.backButton.setOnClickListener {
            finish()
        }

        binding.addToCart.setOnClickListener {
            addItemToCart()
        }
    }

    private fun addItemToCart() {
        val database = FirebaseDatabase.getInstance().reference
        val userId = auth.currentUser?.uid ?: ""
        val cartItem = CartItems(
            foodName.toString(),
            foodPrice.toString(),
            foodDescription.toString(),
            foodImage.toString(),
            1
        )

        database.child(USER_NODE).child(userId).child(CARTITEM_NODE).push().setValue(cartItem)
            .addOnSuccessListener {
                Toast.makeText(this, "Items added to cart successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
            Toast.makeText(this, "Failed to add the item", Toast.LENGTH_SHORT).show()
        }
    }
}