package com.example.hungryhopper.Auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hungryhopper.ChooseLocationActivity
import com.example.hungryhopper.R
import com.example.hungryhopper.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toLogIn = binding.alreadyHaveAcc

        toLogIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val createAcc = binding.signUpbtn
        createAcc.setOnClickListener {
            val intent = Intent(this, ChooseLocationActivity::class.java)
            startActivity(intent)
        }
    }
}