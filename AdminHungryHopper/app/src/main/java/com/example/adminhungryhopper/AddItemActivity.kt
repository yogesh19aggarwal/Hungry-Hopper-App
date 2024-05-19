package com.example.adminhungryhopper

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminhungryhopper.Models.AllMenu
import com.example.adminhungryhopper.Utils.MENU_NODE
import com.example.adminhungryhopper.databinding.ActivityAddItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class AddItemActivity : AppCompatActivity() {

    //Food Item Details
    private lateinit var foodName: String
    private lateinit var foodPrice: String
    private lateinit var foodDescription: String
    private lateinit var foodIngredients: String
    private var foodImageUri: Uri?= null

    // Firebase variables
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    // binding
    private lateinit var binding: ActivityAddItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.addItemBtn.setOnClickListener {
            // get data

            foodName = binding.addFoodName.text.toString()
            foodPrice = binding.addFoodPrice.text.toString()
            foodDescription = binding.description.text.toString()
            foodIngredients = binding.ingredients.text.toString()

            if(foodName.isBlank() || foodPrice.isBlank() || foodDescription.isBlank() || foodIngredients.isBlank()){
                Toast.makeText(this, "Fill all the details", Toast.LENGTH_SHORT).show()
            } else{
                uploadData()
                Toast.makeText(this, "Item Added Successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        binding.selectImage.setOnClickListener {
            pickImage.launch("image/*")
        }

        binding.backBtn.setOnClickListener {
            finish()
        }

    }

    private fun uploadData() {
        // get a reference to menu
        val menuRef: DatabaseReference = database.getReference(MENU_NODE)

        // generate unique key fot the new menu
        val newItemKey = menuRef.push().key

        if(foodImageUri != null){
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("menu_images/${newItemKey}.jpg")
            val uploadTask = imageRef.putFile(foodImageUri!!)

            uploadTask
                .addOnSuccessListener {
                    imageRef.downloadUrl
                        .addOnSuccessListener {
                            downloadUrl->

                            val newItem = AllMenu(
                                newItemKey,
                                foodName = foodName,
                                foodPrice = foodPrice+"₹",
                                foodDescription = foodDescription,
                                foodIngredient = foodIngredients,
                                foodImage = downloadUrl.toString()
                            )

                            newItemKey?.let {
                                key->
                                menuRef.child(key).setValue(newItem)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Data Uploaded", Toast.LENGTH_SHORT).show()

                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(this, "Data Upload Failed", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        }
                }.addOnFailureListener {
                    Toast.makeText(this, "Image Upload Failed", Toast.LENGTH_SHORT).show()
                }

        }else{
            Toast.makeText(this, "Please Select an Image", Toast.LENGTH_SHORT).show()
        }

    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri->
        if(uri != null){
            binding.selectedImage.setImageURI(uri)
            foodImageUri = uri
        }
    }
}