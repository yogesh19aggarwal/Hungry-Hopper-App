package com.example.adminhungryhopper

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminhungryhopper.Adapter.PendingOrderAdapter
import com.example.adminhungryhopper.Models.OrderDetails
import com.example.adminhungryhopper.Utils.AcceptedOrderDetail
import com.example.adminhungryhopper.Utils.BuyHistory
import com.example.adminhungryhopper.Utils.Completed
import com.example.adminhungryhopper.Utils.OrderDetail
import com.example.adminhungryhopper.Utils.USER_NODE
import com.example.adminhungryhopper.databinding.ActivityPendingOrderBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PendingOrderActivity : AppCompatActivity(), PendingOrderAdapter.OnItemClicked {

    lateinit var binding: ActivityPendingOrderBinding
    private var lisOfName: MutableList<String> = mutableListOf()
    private var lisOfTotalPrice: MutableList<String> = mutableListOf()
    private var foodQuantities: MutableList<Int> = mutableListOf()
    private var lisOfImageFirstFoodOrder: MutableList<String> = mutableListOf()
    private var lisOfOrderItem: ArrayList<OrderDetails> = arrayListOf()

    private lateinit var database: FirebaseDatabase
    private lateinit var databaseOrderDetails: DatabaseReference
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

        databaseOrderDetails.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (orderSnapshot in snapshot.children) {
                    val orderDetails = orderSnapshot.getValue(OrderDetails::class.java)

                    orderDetails?.let {
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

        for (orderItem in lisOfOrderItem) {

            orderItem.userName?.let {
                lisOfName.add(it)
            }
            orderItem.totalPrice?.let {
                lisOfTotalPrice.add(it)
            }
            orderItem.foodImage?.filterNot { it.isEmpty() }?.forEach {
                lisOfImageFirstFoodOrder.add(it)
            }

            orderItem.foodQuantity?.forEach {
                foodQuantities.add(it)
            }
        }
        setAdapter()
    }

    private fun setAdapter() {
        binding.pendingRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter =
            PendingOrderAdapter(lisOfName, lisOfTotalPrice, lisOfImageFirstFoodOrder, this, this)

        binding.pendingRecyclerView.adapter = adapter
    }

    override fun onItemClickedListener(position: Int) {
        val intent = Intent(this, OrderDetailsActivity::class.java)
        val userOrderDetails = lisOfOrderItem[position]
        intent.putExtra("UserOrderDetails", userOrderDetails)
        startActivity(intent)
    }

    override fun onItemAcceptClickListener(position: Int) {
        val childItemPushKey = lisOfOrderItem[position].itemPushKey
        val clickItemOrderRef = childItemPushKey?.let {
            database.reference.child(OrderDetail).child(it)
        }

        clickItemOrderRef?.child(AcceptedOrderDetail)?.setValue(true)
        updateOrderAcceptStatus(position)
    }

    override fun onItemDispatchClickListener(position: Int) {
        val dispatchedItemPushKey = lisOfOrderItem[position].itemPushKey
        val dispatchItemOrderRef = database.reference.child(Completed).child(dispatchedItemPushKey!!)
        dispatchItemOrderRef.setValue(lisOfOrderItem[position])
            .addOnSuccessListener {
                deleteThisItemFromOrderDetails(dispatchedItemPushKey)
            }
    }

    private fun deleteThisItemFromOrderDetails(dispatchedItemPushKey: String) {
        val orderDetailsItemsRef = database.reference.child(OrderDetail).child(dispatchedItemPushKey)
        orderDetailsItemsRef.removeValue()
            .addOnSuccessListener {
                Toast.makeText(this, "Order is dispatched", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Order is not dispatched", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateOrderAcceptStatus(position: Int) {
        val userIdOfClickedItem = lisOfOrderItem[position].userId
        val pushKeyOfClickedItem = lisOfOrderItem[position].itemPushKey
        val buyHistoryRef =
            database.reference.child(USER_NODE).child(userIdOfClickedItem!!).child(BuyHistory)
                .child(pushKeyOfClickedItem!!)
        buyHistoryRef.child(AcceptedOrderDetail).setValue(true)

        databaseOrderDetails.child(pushKeyOfClickedItem).child(AcceptedOrderDetail).setValue(true)
    }
}