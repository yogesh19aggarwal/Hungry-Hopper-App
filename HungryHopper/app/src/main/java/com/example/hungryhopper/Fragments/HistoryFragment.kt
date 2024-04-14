package com.example.hungryhopper.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hungryhopper.R
import com.example.hungryhopper.adapter.BuyAgainAdapter
import com.example.hungryhopper.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var buyAgainAdapter: BuyAgainAdapter

    val buyAgainFoodName = arrayListOf("Food 2", "Food 3", "Food 4")
    val buyAgainFoodPrice = arrayListOf("$10", "$20", "$30")
    val buyAgainFoodImage = arrayListOf(
        R.drawable.menu1,
        R.drawable.menu2,
        R.drawable.menu3,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)

        setUpRecyclerView()

        binding.apply {
            historyFood.text = buyAgainFoodName[0]
            historyPrice.text = buyAgainFoodPrice[0]
            historyImage.setImageResource(buyAgainFoodImage[0])
        }

        return binding.root
    }
    private fun setUpRecyclerView(){


        buyAgainAdapter = BuyAgainAdapter(buyAgainFoodName, buyAgainFoodPrice, buyAgainFoodImage)

        binding.historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.historyRecyclerView.adapter = buyAgainAdapter
    }
    companion object {

    }
}