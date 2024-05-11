package com.example.hungryhopper.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.hungryhopper.R
import com.example.hungryhopper.Utils.BuyHistory
import com.example.hungryhopper.Model.OrderDetails
import com.example.hungryhopper.RecentOrderItems
import com.example.hungryhopper.Utils.USER_NODE
import com.example.hungryhopper.adapter.BuyAgainAdapter
import com.example.hungryhopper.databinding.FragmentHistoryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.io.Serializable


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var buyAgainAdapter: BuyAgainAdapter

    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var userId: String
    private var lisOfOrderItem: MutableList<OrderDetails> = mutableListOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        //
        retrieveByHistory()

        binding.recentBuyItem.setOnClickListener {
            seeItemsRecentBuy()
        }

        return binding.root
    }

    private fun seeItemsRecentBuy() {
        lisOfOrderItem.firstOrNull()?.let {

        val intent = Intent(requireContext(), RecentOrderItems::class.java)
            intent.putExtra("RecentOrderItem", lisOfOrderItem as Serializable)
            startActivity(intent)
        }
    }

    private fun retrieveByHistory() {

        binding.recentBuyItem.visibility = View.INVISIBLE

        userId = auth.currentUser?.uid ?: ""

        val buyItemRef = database.reference.child(USER_NODE).child(userId).child(BuyHistory)
        val shortingQuery = buyItemRef.orderByChild("currentTime")

        shortingQuery.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for(buySnapshot:DataSnapshot in snapshot.children){
                    val buyHistoryItem = buySnapshot.getValue(OrderDetails::class.java)
                    buyHistoryItem?.let{
                        lisOfOrderItem.add(it)
                    }
                }
                lisOfOrderItem.reverse()
                if(lisOfOrderItem.isNotEmpty()){
                    setDataInRecentBuyItem()
                    setPreviousBuyItemsRecyclerview()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun setDataInRecentBuyItem() {

        binding.recentBuyItem.visibility = View.VISIBLE

        val recentOrderitem = lisOfOrderItem.firstOrNull()

        recentOrderitem?.let {
            with(binding){
                historyFood.text = it.foodName?.firstOrNull()?:""
                historyPrice.text = it.foodPrices?.firstOrNull()?:""
                val image = it.foodImage?.firstOrNull()?:""
                val uri = Uri.parse(image)
                Glide.with(requireContext()).load(uri).into(historyImage)

                lisOfOrderItem.reverse()

            }
        }
    }

    private fun setPreviousBuyItemsRecyclerview() {
        val buyAgainFoodName = mutableListOf<String>()
        val buyAgainFoodPrice = mutableListOf<String>()
        val buyAgainFoodImage = mutableListOf<String>()
        for(i in 1 until lisOfOrderItem.size){
            lisOfOrderItem[i].foodName?.firstOrNull()?.let {
                buyAgainFoodName.add(it)
            }
            lisOfOrderItem[i].foodPrices?.firstOrNull()?.let {
                buyAgainFoodPrice.add(it)
            }
            lisOfOrderItem[i].foodImage?.firstOrNull()?.let {
                buyAgainFoodImage.add(it)
            }

        }
        buyAgainAdapter = BuyAgainAdapter(buyAgainFoodName, buyAgainFoodPrice, buyAgainFoodImage, requireContext())

        binding.historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.historyRecyclerView.adapter = buyAgainAdapter
    }


}