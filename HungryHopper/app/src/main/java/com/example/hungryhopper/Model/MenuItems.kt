package com.example.hungryhopper.Model

data class MenuItems(
    val foodName: String ?= null,
    val foodPrice: String ?= null,
    val foodDescription: String ?= null,
    val foodImage: String ?= null,
    val foodIngredient: String ?= null
)
