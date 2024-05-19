package com.example.adminhungryhopper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminhungryhopper.Adapter.DeliveryAdapter
import com.example.adminhungryhopper.Models.OrderDetails
import com.example.adminhungryhopper.Utils.Completed
import com.example.adminhungryhopper.databinding.ActivityOutForDeliveryBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OutForDeliveryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutForDeliveryBinding

    private lateinit var database: FirebaseDatabase
    private var listOfCompleteOrderList: ArrayList<OrderDetails> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityOutForDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//        dummy data
//        val customerName = arrayListOf("John Doe","Jane Smith","Mike Johnson")
//
//        val moneyStatus = arrayListOf( "Recieved", "Not Recieved","Pending")

        binding.backBtn.setOnClickListener {
            finish()
        }

        retrieveCompleteOrderDetails()




    }

    private fun retrieveCompleteOrderDetails() {

        database = FirebaseDatabase.getInstance()
        val completeOrderRef = database.reference.child(Completed)
            .orderByChild("currentTime")

        completeOrderRef.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                listOfCompleteOrderList.clear()

                for(orderSnapshot in snapshot.children){
                    val completeOrder = orderSnapshot.getValue(OrderDetails::class.java)
                    completeOrder?.let {
                        listOfCompleteOrderList.add(it)
                    }
                }
                listOfCompleteOrderList.reverse()

                setDataIntoRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun setDataIntoRecyclerView() {

        val customerName = mutableListOf<String>()
        val moneyStatus = mutableListOf<Boolean>()

        for(order in listOfCompleteOrderList){
            order.userName?.let {
                customerName.add(it)
            }
            order.paymentRecieved?.let {
                moneyStatus.add(it)
            }
        }

        val adapter = DeliveryAdapter(customerName, moneyStatus)

        binding.deliveryRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.deliveryRecyclerView.adapter = adapter
    }
}