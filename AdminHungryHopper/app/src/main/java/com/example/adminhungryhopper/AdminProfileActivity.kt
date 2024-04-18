package com.example.adminhungryhopper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminhungryhopper.databinding.ActivityAdminProfileBinding

class AdminProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminProfileBinding

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

        binding.backBtn.setOnClickListener {
            finish()
        }

        val name = binding.name
        val email = binding.email
        val address = binding.address
        val phoneNumber = binding.phoneNumber
        val password = binding.password

        name.isEnabled = false
        email.isEnabled = false
        address.isEnabled = false
        phoneNumber.isEnabled = false
        password.isEnabled = false

        binding.clickToEdit.setOnClickListener {

            name.isEnabled = true
            email.isEnabled = true
            address.isEnabled = true
            phoneNumber.isEnabled = true
            password.isEnabled = true
        }
    }
}