package com.example.adminhungryhopper.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adminhungryhopper.databinding.PendingOrderItemBinding

class PendingOrderAdapter(private val customerNames: MutableList<String>, private val totalPriceList: MutableList<String>, private val foodImages: MutableList<String>,
                          private val itemClicked: OnItemClicked,
                          private val context: Context): RecyclerView.Adapter<PendingOrderAdapter.PendingOrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingOrderViewHolder {
        val binding = PendingOrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PendingOrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return customerNames.size
    }

    override fun onBindViewHolder(holder: PendingOrderViewHolder, position: Int) {

        holder.bind(position)
    }

    inner class PendingOrderViewHolder(private val binding: PendingOrderItemBinding): RecyclerView.ViewHolder(binding.root) {

        private var isAccepted = false

        fun bind(position: Int) {
            binding.apply{
                customerName.text = customerNames[position]
                quantity.text = totalPriceList[position]

                var uriString = foodImages[position]
                val uri = Uri.parse(uriString)
                Glide.with(context).load(uri).into(orderFoodImage)

                orderAcceptButton.apply {
                    if(!isAccepted){
                        text = "Accept"
                    }else{
                        text = "Dispatch"
                    }
                    setOnClickListener {
                        if(!isAccepted){
                            text = "Dispatch"
                            isAccepted = true
                            showToast("Orderes is Accepted")
                            itemClicked.onItemAcceptClickListener(position)
                        }else{
                            customerNames.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            showToast("Order is Dispatched")
                            itemClicked.onItemDispatchClickListener(position)
                        }
                    }

                }

                itemView.setOnClickListener{
                    itemClicked.onItemClickedListener(position)
                }
            }

        }

    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    interface OnItemClicked{
        fun onItemClickedListener(position: Int)
        fun onItemAcceptClickListener(position: Int)
        fun onItemDispatchClickListener(position: Int)
    }
}