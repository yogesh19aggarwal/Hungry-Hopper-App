package com.example.adminhungryhopper.Auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminhungryhopper.MainActivity
import com.example.adminhungryhopper.Models.UserModel
import com.example.adminhungryhopper.R
import com.example.adminhungryhopper.Utils.Admin_USER_NODE
import com.example.adminhungryhopper.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var userName: String
    private lateinit var nameOfRestaurant: String
    private lateinit var database: DatabaseReference

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Initializing auth by firebase
        auth = Firebase.auth

        //Initializing database by firebase
        database = Firebase.database.reference


        //setting up the location by dummy data
        val locationList = arrayOf("Jaipur", "Odisha", "Bundi", "Sikar")

        // setting up adapter on recycler view
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, locationList)
        val autoCompleteText = binding.listOfLocation
        autoCompleteText.setAdapter(adapter)


        //create account button
        binding.createAccountBtn.setOnClickListener {

            email = binding.signUpEmail.text.toString().trim()
            userName = binding.signUpName.text.toString()
            nameOfRestaurant = binding.signUpRestaurant.text.toString()
            password = binding.signUpPass.text.toString().trim()

            if(userName.isBlank() || nameOfRestaurant.isBlank() || password.isBlank() || email.isBlank()){
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            }else{
                createAccount(email, password)
            }
        }

        // already have account button
        binding.alreadyBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            task->
            if(task.isSuccessful){
                Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show()

                saveUserData()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this, "Account creating failed", Toast.LENGTH_SHORT).show()
                Log.d("Account", "createAccount: Failure", task.exception)
            }
        }
    }

    // save user data in to realtime data base
    private fun saveUserData() {

        email = binding.signUpEmail.text.toString().trim()
        userName = binding.signUpName.text.toString()
        nameOfRestaurant = binding.signUpRestaurant.text.toString()
        password = binding.signUpPass.text.toString().trim()

        val user = UserModel(userName, email, password, nameOfRestaurant)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        database.child(Admin_USER_NODE).child(userId).setValue(user)
    }
}