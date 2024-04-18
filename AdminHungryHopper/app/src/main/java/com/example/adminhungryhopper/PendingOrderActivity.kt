package com.example.adminhungryhopper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminhungryhopper.Adapter.DeliveryAdapter
import com.example.adminhungryhopper.Adapter.PendingOrderAdapter
import com.example.adminhungryhopper.databinding.ActivityPendingOrderBinding

class PendingOrderActivity : AppCompatActivity() {

    lateinit var binding: ActivityPendingOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPendingOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val customerName = arrayListOf(
            "John Doe",
            "Jane Smith",
            "Mike Johnson"
        )

        val orderQuantity = arrayListOf(
            "8",
            "3",
            "6"
        )

        val menuImage = arrayListOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3
        )

        binding.backBtn.setOnClickListener {
            finish()
        }

        val adapter = PendingOrderAdapter(customerName, orderQuantity, menuImage, this)

        binding.pendingRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.pendingRecyclerView.adapter = adapter
    }
}