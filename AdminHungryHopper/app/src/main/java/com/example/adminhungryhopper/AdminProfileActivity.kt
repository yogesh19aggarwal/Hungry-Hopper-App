package com.example.adminhungryhopper

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminhungryhopper.Models.UserModel
import com.example.adminhungryhopper.Utils.Admin_USER_NODE
import com.example.adminhungryhopper.Utils.USER_NODE
import com.example.adminhungryhopper.databinding.ActivityAdminProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AdminProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminProfileBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var adminRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAdminProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        adminRef = database.reference.child(Admin_USER_NODE)

        binding.backBtn.setOnClickListener {
            finish()
        }

        val name = binding.name
        val email = binding.email
        val address = binding.address
        val phoneNumber = binding.phoneNumber
        val password = binding.password
        val saveInfo = binding.saveInfoButton

        saveInfo.setOnClickListener {
            setUserData()
        }

        name.isEnabled = false
        email.isEnabled = false
        address.isEnabled = false
        phoneNumber.isEnabled = false
        password.isEnabled = false
        saveInfo.isEnabled = false

        binding.clickToEdit.setOnClickListener {

            name.isEnabled = true
            email.isEnabled = true
            address.isEnabled = true
            phoneNumber.isEnabled = true
            password.isEnabled = true
            saveInfo.isEnabled = true
        }

        retrieveUserData()
    }

    private fun setUserData() {
        val name = binding.name
        val emailEdit = binding.email
        val addressEdit = binding.address
        val phoneNumber = binding.phoneNumber
        val password = binding.password


        var updateName = name.text.toString()
        var updateEmail = emailEdit.text.toString()
        var updateAddress = addressEdit.text.toString()
        var updatePass = password.text.toString()
        var updatePhone = phoneNumber.text.toString()
        var updateRestaurant = binding.restaurantName.text.toString()
        val currrentUserUid = auth.currentUser?.uid

        if(currrentUserUid != null){
            val userRef = adminRef.child(currrentUserUid)
            var userData = UserModel(updateName, updateEmail, updatePass, updateRestaurant, updateAddress, updatePhone)

            userRef.setValue(userData).addOnSuccessListener {
                Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()
                auth.currentUser?.updateEmail(updateEmail)
                auth.currentUser?.updatePassword(updatePass)
            }.addOnFailureListener {
                Toast.makeText(this, "Profile Update Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun retrieveUserData() {

        val currentUserUid = auth.currentUser?.uid

        if (currentUserUid != null) {
            val userRef = adminRef.child(currentUserUid)
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        var ownerName = snapshot.child("name").value
                        var email = snapshot.child("email").value
                        var pass = snapshot.child("password").value
                        var address = snapshot.child("address").value
                        var phone = snapshot.child("phoneNum").value
                        val restarauntName = snapshot.child("nameOfRestaurant").value

                        setDataToTextView(ownerName, email, pass, address, phone, restarauntName)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }

    }

    private fun setDataToTextView(
        ownerName: Any?,
        email: Any?,
        pass: Any?,
        address: Any?,
        phone: Any?,
        restarauntName: Any?
    ) {
        val name = binding.name
        val emailEdit = binding.email
        val addressEdit = binding.address
        val phoneNumber = binding.phoneNumber
        val password = binding.password
        val restaurantName = binding.restaurantName

        name.setText(ownerName.toString())
        emailEdit.setText(email.toString())
        addressEdit.setText(address.toString())
        password.setText(pass.toString())
        phoneNumber.setText(phone.toString())
        restaurantName.setText(restarauntName.toString())
    }
}