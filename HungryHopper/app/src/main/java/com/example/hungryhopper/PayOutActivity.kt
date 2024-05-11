package com.example.hungryhopper

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hungryhopper.Model.OrderDetails
import com.example.hungryhopper.Utils.BuyHistory
import com.example.hungryhopper.Utils.CARTITEM_NODE
import com.example.hungryhopper.Utils.OrderDetail
import com.example.hungryhopper.Utils.USER_NODE
import com.example.hungryhopper.databinding.ActivityPayOutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PayOutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPayOutBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var name:String
    private lateinit var address:String
    private lateinit var phone: String
    private lateinit var totalAmount:String
    private lateinit var foodItemName: ArrayList<String>
    private lateinit var foodItemPrice: ArrayList<String>
    private lateinit var foodItemDescription: ArrayList<String>
    private lateinit var foodItemImage: ArrayList<String>
    private lateinit var foodItemIngredients: ArrayList<String>
    private lateinit var foodItemQuantity: ArrayList<Int>
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userId: String

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

        auth = FirebaseAuth.getInstance()

        databaseReference = FirebaseDatabase.getInstance().reference

        setUserData()

        val backToCartBtn = binding.backToCart
        val payOut = binding.payOutBtn


        backToCartBtn.setOnClickListener {
            finish()
        }

        payOut.setOnClickListener {

            name = binding.name.text.toString()
            address = binding.address.text.toString().trim()
            phone = binding.phoneNumber.text.toString()

            if(name.isBlank() && address.isBlank() && phone.isBlank()){
                Toast.makeText(this@PayOutActivity, "Please all the details" , Toast.LENGTH_SHORT).show()
            } else{
                placeOrder()
            }

        }

        val intent = intent
        foodItemName = intent.getStringArrayListExtra("FoodItemName") as ArrayList<String>
        foodItemPrice = intent.getStringArrayListExtra("FoodItemPrice") as ArrayList<String>
        foodItemDescription = intent.getStringArrayListExtra("FoodItemDescription") as ArrayList<String>
        foodItemImage = intent.getStringArrayListExtra("FoodItemImage") as ArrayList<String>
        foodItemIngredients = intent.getStringArrayListExtra("FoodItemIngredients") as ArrayList<String>
        foodItemQuantity = intent.getIntegerArrayListExtra("FoodItemQuantity") as ArrayList<Int>

        totalAmount = calculateTotalAmount().toString() + "₹"

        binding.totalAmount.isEnabled = false
        binding.totalAmount.setText(totalAmount)

    }

    private fun placeOrder() {
        userId = auth.currentUser?.uid?:""
        val time = System.currentTimeMillis()
        val itemPushkey = databaseReference.child(OrderDetail).push().key
        val orderDetails = OrderDetails(userId, name, foodItemName, foodItemPrice, foodItemImage, foodItemQuantity, address, totalAmount, phone, time, itemPushkey, false, false)
        val orderRef = databaseReference.child(OrderDetail).child(itemPushkey!!)

        orderRef.setValue(orderDetails).addOnSuccessListener {
            val bottomSheetDiaglog = CongratsBottomSheet()
            bottomSheetDiaglog.show(supportFragmentManager, "Test")
            removeItemFromCart()
            addOrderToHistory(orderDetails)
        }
    }

    private fun addOrderToHistory(orderDetails: OrderDetails) {
        databaseReference.child(USER_NODE).child(userId).child(BuyHistory)
            .child(orderDetails.itemPushKey!!)
            .setValue(orderDetails).addOnSuccessListener {

            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to order", Toast.LENGTH_SHORT).show()
            }
    }

    private fun removeItemFromCart() {
        val cartItemsRef = databaseReference.child(USER_NODE).child(userId).child(CARTITEM_NODE).removeValue()
    }

    private fun calculateTotalAmount(): Int {

        var totalAmount = 0;
        for(i in 0 until foodItemPrice.size){
            var price = foodItemPrice[i]
            val lastChar = price.last()
            val priceIntValue = if(lastChar == '₹'){
                price.dropLast(1).toInt()
            } else{
                price.toInt()
            }

            var quantity = foodItemQuantity[i]
            totalAmount += priceIntValue *quantity
        }
        return totalAmount
    }

    private fun setUserData() {
        val user = auth.currentUser
        if(user != null){
            val userId = user.uid
            val userRef = databaseReference.child(USER_NODE).child(userId)

            userRef.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        val names = snapshot.child("name").getValue(String::class.java)?:""
                        val addresses = snapshot.child("address").getValue(String::class.java)?:""
                        val phones = snapshot.child("phoneNum").getValue(String::class.java)?:""

                        binding.apply {
                            name.setText(names)
                            address.setText(addresses)
                            phoneNumber.setText(phones)
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@PayOutActivity, "Failed" , Toast.LENGTH_SHORT).show()
                }


            })
        }
    }
}