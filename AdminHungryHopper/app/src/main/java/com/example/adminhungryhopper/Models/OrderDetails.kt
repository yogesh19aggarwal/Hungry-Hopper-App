package com.example.adminhungryhopper.Models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class OrderDetails() : Serializable{

    var userId: String ?= null
    var userName: String ?= null
    var foodName: MutableList<String> ?= null
    var foodImage: MutableList<String> ?= null
    var foodPrices: MutableList<String> ?= null
    var foodQuantity: MutableList<Int> ?= null
    var address: String ?= null
    var totalPrice: String ?= null
    var phoneNumber: String ?= null
    var orderAccepted: Boolean ?= false
    var paymentRecieved: Boolean ?= false
    var itemPushKey: String ?= null
    var currentTime: Long ?= 0

    constructor(parcel: Parcel) : this() {
        userId = parcel.readString()
        userName = parcel.readString()
        address = parcel.readString()
        totalPrice = parcel.readString()
        phoneNumber = parcel.readString()
        orderAccepted = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        paymentRecieved = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        itemPushKey = parcel.readString()
        currentTime = parcel.readValue(Long::class.java.classLoader) as? Long
    }

    constructor(
        userId: String,
        name: String,
        foodItemName: ArrayList<String>,
        foodItemPrice: ArrayList<String>,
        foodItemImage: ArrayList<String>,
        foodItemQuantity: ArrayList<Int>,
        address: String,
        totalAmount: String,
        phone: String,
        time: Long,
        itemPushkey: String?,
        b: Boolean,
        b1: Boolean
    ) : this(){
        this.userId = userId
        this.userName = name
        this.foodName = foodItemName
        this.foodPrices = foodItemPrice
        this.foodImage = foodItemImage
        this.foodQuantity = foodItemQuantity
        this.address = address
        this.totalPrice = totalAmount
        this.phoneNumber = phone
        this.currentTime = time
        this.itemPushKey = itemPushkey
        this.orderAccepted = b
        this.paymentRecieved = b1
    }

    fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(userName)
        parcel.writeString(address)
        parcel.writeString(totalPrice)
        parcel.writeString(phoneNumber)
        parcel.writeValue(orderAccepted)
        parcel.writeValue(paymentRecieved)
        parcel.writeString(itemPushKey)
        parcel.writeValue(currentTime)
    }

    fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderDetails> {
        override fun createFromParcel(parcel: Parcel): OrderDetails {
            return OrderDetails(parcel)
        }

        override fun newArray(size: Int): Array<OrderDetails?> {
            return arrayOfNulls(size)
        }
    }
}