package com.example.hungryhopper

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hungryhopper.Fragments.CartFragment
import com.example.hungryhopper.databinding.ActivityPayOutBinding

class PayOutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPayOutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPayOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backToCartBtn = binding.backToCart
        val payOut = binding.payOutBtn
        val bottomSheetDiaglog = CongratsBottomSheet()


        backToCartBtn.setOnClickListener {

            finish()
        }

        payOut.setOnClickListener {
            bottomSheetDiaglog.show(supportFragmentManager, "Test")
        }

    }
}