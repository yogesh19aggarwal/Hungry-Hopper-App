package com.example.hungryhopper.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hungryhopper.Model.UserModel
import com.example.hungryhopper.R
import com.example.hungryhopper.databinding.ActivityDetailsBinding
import com.example.hungryhopper.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private var auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        setUserData()
        binding.saveBtn.setOnClickListener {
            val name = binding.name.text.toString()
            val email = binding.email.text.toString()
            val address = binding.address.text.toString()
            val phoneNum = binding.phoneNumber.text.toString()

            updateUserData(name, email, address, phoneNum)
        }
        return binding.root
    }

    private fun updateUserData(name: String, email: String, address: String, phoneNum: String) {

        val userId = auth.currentUser?.uid

        if(userId != null){
            val userRef = database.getReference("User").child(userId)
            val userData = hashMapOf(
                "name" to name,
                "address" to address,
                "email" to email,
                "phoneNum" to phoneNum
                )

            userRef.setValue(userData).addOnSuccessListener {
                Toast.makeText(requireContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Profile Update Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUserData() {
        val userId = auth.currentUser?.uid

        if(userId != null){
            val userRef = database.getReference("User").child(userId)
            userRef.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        val userProfile = snapshot.getValue(UserModel::class.java)

                        if(userProfile != null){
                            binding.name.setText(userProfile.name)
                            binding.email.setText(userProfile.email)
                            binding.phoneNumber.setText(userProfile.phoneNum)
                            binding.address.setText(userProfile.address)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

}