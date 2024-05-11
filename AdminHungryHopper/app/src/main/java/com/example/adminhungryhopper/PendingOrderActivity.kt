package com.example.adminhungryhopper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminhungryhopper.Models.OrderDetails
import com.example.adminhungryhopper.Utils.OrderDetail
import com.example.adminhungryhopper.databinding.ActivityPendingOrderBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PendingOrderActivity : AppCompatActivity() {

    lateinit var binding: ActivityPendingOrderBinding
    private var lisOfName: MutableList<String> = mutableListOf()
    private var lisOfTotalPrice: MutableList<String> = mutableListOf()
    private var lisOfImageFirstFoodOrder: MutableList<String> = mutableListOf()
    private var lisOfOrderItem: MutableList<OrderDetails> = mutableListOf()

    private lateinit var database: FirebaseDatabase
    private lateinit var databaseOrderDetails:DatabaseReference
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

        // dummy data
//        val customerName = arrayListOf("John Doe", "Jane Smith", "Mike Johnson")
//        val orderQuantity = arrayListOf( "8", "3", "6"  )
//        val menuImage = arrayListOf( R.drawable.menu1,R.drawable.menu2, R.drawable.menu3 )

        binding.backBtn.setOnClickListener {
            finish()
        }

        database = FirebaseDatabase.getInstance()
        databaseOrderDetails = database.reference.child(OrderDetail)

        getOrdersDetails()

    }

    private fun getOrdersDetails() {

        databaseOrderDetails.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for(orderSnapshot in snapshot.children){
                    val orderDetails = orderSnapshot.getValue(OrderDetails::class.java)

                    orderDetails?.let{
                        lisOfOrderItem.add(it)
                    }
                }

                addDataToListForRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun addDataToListForRecyclerView() {

        for(orderItem in lisOfOrderItem){

            
        }
    }
}